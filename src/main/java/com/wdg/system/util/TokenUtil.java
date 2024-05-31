package com.wdg.system.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.jwt.JWTUtil;
import com.wdg.common.config.TokenProperties;
import com.wdg.common.constant.RedisConstants;
import com.wdg.common.constant.ResultCode;
import com.wdg.common.exception.BusinessException;
import com.wdg.common.util.MyServletUtil;
import com.wdg.common.util.RedisCache;
import com.wdg.system.dto.LoginInfoDTO;
import com.wdg.system.dto.LoginSessionDTO;
import com.wdg.system.dto.LoginTokenDTO;
import com.wdg.system.entity.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description: token工具类
 * @author: wdg
 * @date: 2024/5/17
 **/
@Component
public class TokenUtil {

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AsyncService asyncService;

    /**
     * 生成token
     *
     * @return
     */
    public String generateToken(SysUser sysUser) {

        Long userId = sysUser.getUserId();
        //创建token
        String tokenKey = userId + ":" + IdUtil.getSnowflakeNextIdStr();
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("tokenKey", tokenKey);
        String token = JWTUtil.createToken(map, tokenProperties.getSecret().getBytes());

        //填充LoginTokenDTO对象（本次登录的基本信息） 并存到redis
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = dateFormat.format(new Date());
        String clientIP = ServletUtil.getClientIP(MyServletUtil.getRequest());
        LoginTokenDTO loginTokenDTO = new LoginTokenDTO();
        loginTokenDTO.setTokenKey(tokenKey);
        loginTokenDTO.setCreteDate(currentDate);
        loginTokenDTO.setUpdateDate(currentDate);
        loginTokenDTO.setLoginIp(clientIP);
        redisCache.setCacheObject(RedisConstants.LOGIN_TOKEN + tokenKey, loginTokenDTO, tokenProperties.getExpireTime(), TimeUnit.MINUTES);

        //异步处理会话和token （在不影响效率的前提下，避免同一账号多线程并发登录导致的竞态条件问题）
        asyncService.asyncSession(sysUser, loginTokenDTO);
        return token;
    }


    /**
     * 校验token
     */
    public Boolean verifyToken(String token) {
        String tokenKey = JWTUtil.parseToken(token).getPayload("tokenKey").toString();

        return true;
    }


    /**
     * 获取当前登录信息
     *
     * @return
     */
    public LoginInfoDTO getLoginInfo() {
        String token = MyServletUtil.getRequest().getHeader(tokenProperties.getHeader());
        String userId = JWTUtil.parseToken(token).getPayload("userId").toString();
        LoginSessionDTO loginSessionDTO = redisCache.getCacheObject(RedisConstants.LOGIN_SESSION + userId);

        LoginInfoDTO loginInfoDTO = new LoginInfoDTO();
        BeanUtils.copyProperties(loginSessionDTO, loginInfoDTO);
        return loginInfoDTO;
    }


    /**
     * 登录密码错误次数+1
     *
     * @param userId
     */
    public void loginPasswordError(Long userId) {
        Long count = redisCache.incrementCacheObject(RedisConstants.LOGIN_ERROR + userId);
        redisCache.expire(RedisConstants.LOGIN_ERROR + userId, tokenProperties.getPwdErrorTime(), TimeUnit.MINUTES);

        //密码错误次数达到最大值，锁定登录
        if (count >= tokenProperties.getPwdErrorMax()) {
            redisCache.setCacheObject(RedisConstants.LOGIN_LOCK + userId, "pwd_error_lock", tokenProperties.getPwdErrorLockTime(), TimeUnit.MINUTES);
        }
    }


    /**
     * 检测登录是否被锁定
     *
     * @param userId
     */
    public void checkLoginLock(Long userId) {
        Boolean flag = redisCache.hasKey(RedisConstants.LOGIN_LOCK + userId);
        if (flag) {
            throw new BusinessException(ResultCode.USER_PASSWORD_ERROR_OVER_MAX_LIMIT);
        }

    }
}
