package com.wdg.system.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.wdg.common.constant.RedisConstants;
import com.wdg.common.dto.result.ApiResult;
import com.wdg.common.enums.ResultCode;
import com.wdg.common.utils.MyServletUtil;
import com.wdg.common.utils.RedisCache;
import com.wdg.system.dto.SysUserToken;
import com.wdg.system.entity.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Token服务类
 */
@Component
public class TokenService {

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    //令牌过期时间
    @Value("${token.expireTime}")
    private String expireTime;

    //续期警戒时间
    @Value("${token.alarmTime}")
    private String alarmTime;

    @Resource
    private RedisCache redisCache;

    /***
     * 生成token，并把系统用户存入redis
     * @param sysUser
     * @return
     */
    public String generateToken(SysUser sysUser) {

        //创建token
        String tokenKey = IdUtil.getSnowflakeNextIdStr();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", sysUser.getUserId());
        map.put("userAccount", sysUser.getUserAccount());
        map.put("tokenKey", tokenKey);

        SysUserToken sysUserToken = new SysUserToken();
        BeanUtils.copyProperties(sysUser, sysUserToken);
        sysUserToken.setTokenKey(tokenKey);

        //获取登录IP
        String clientIP = ServletUtil.getClientIP(MyServletUtil.getRequest());
        sysUserToken.setLoginIp(clientIP);

        //存入redis
        redisCache.setCacheObject(getRedisKey(tokenKey), sysUserToken, Integer.valueOf(expireTime), TimeUnit.MINUTES);

        return JWTUtil.createToken(map, secret.getBytes());
    }


    /**
     * 验证JWT令牌的有效性,相差不足{{alarmTime}}分钟，自动刷新缓存
     *
     * @param response
     * @param token
     * @return
     */
    public boolean verifyToken(HttpServletResponse response, String token) {
        if (StrUtil.isEmpty(token)) {
            String str = JSONUtil.toJsonStr(new ApiResult(ResultCode.TOKEN_EXCEPTION.getCode(), "token不存在"));
            MyServletUtil.renderString(response, str);
            return false;
        }
        boolean verify = false;
        try {
            verify = JWTUtil.verify(token, secret.getBytes());
        } catch (Exception e) {
            String str = JSONUtil.toJsonStr(new ApiResult(ResultCode.TOKEN_EXCEPTION.getCode(), "token错误"));
            MyServletUtil.renderString(response, str);
            return false;
        }
        if (!verify) {
            String str = JSONUtil.toJsonStr(new ApiResult(ResultCode.TOKEN_EXCEPTION.getCode(), "token是无效的"));
            MyServletUtil.renderString(response, str);
            return false;
        }
        JWT jwt = JWTUtil.parseToken(token);
        String tokenKey = jwt.getPayload("tokenKey").toString();
        SysUserToken sysUserToken = redisCache.getCacheObject(getRedisKey(tokenKey));

        if (sysUserToken == null) {
            String str = JSONUtil.toJsonStr(new ApiResult(ResultCode.TOKEN_EXCEPTION.getCode(), "token已过期"));
            MyServletUtil.renderString(response, str);
            return false;
        }
        long expire = redisCache.getExpire(getRedisKey(tokenKey));
        if (expire < Long.parseLong(alarmTime) * 60 * 1000) {
            redisCache.setCacheObject(getRedisKey(tokenKey), sysUserToken, Integer.valueOf(expireTime), TimeUnit.MINUTES);
        }
        return true;
    }


    /**
     * 封装redis的key
     *
     * @param tokenKey
     * @return
     */
    private String getRedisKey(String tokenKey) {
        return RedisConstants.SYS_USER_TOKEN + tokenKey;
    }


    /**
     * 删除token
     *
     * @param response
     * @param token
     */
    public void delToken(HttpServletResponse response, String token) {
        String str = "";
        try {
            JWT jwt = JWTUtil.parseToken(token);
            String tokenKey = jwt.getPayload("tokenKey").toString();
            redisCache.deleteObject(getRedisKey(tokenKey));
            str = JSONUtil.toJsonStr(ApiResult.success("退出成功"));
        } catch (Exception e) {
            str = JSONUtil.toJsonStr(new ApiResult(ResultCode.TOKEN_EXCEPTION.getCode(), "token是无效的"));
        }
        MyServletUtil.renderString(response, str);
    }


}
