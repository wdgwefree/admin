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
import com.wdg.system.dto.LoginSessionDTO;
import com.wdg.system.dto.LoginTokenDTO;
import com.wdg.system.entity.SysMenu;
import com.wdg.system.entity.SysRole;
import com.wdg.system.entity.SysUser;
import com.wdg.system.service.ISysMenuService;
import com.wdg.system.service.ISysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
    private ISysMenuService iSysMenuService;

    @Autowired
    private ISysRoleService iSysRoleService;

    @Autowired
    private RedisCache redisCache;


    /**
     * 生成token
     *
     * @return
     */
    public String generateToken(SysUser sysUser) {

        Long userId = sysUser.getUserId();

        //用户角色、权限查询
        List<String> permissions = listPermissionByUserId(userId);
        List<String> roles = listRoleByUserId(userId);

        //创建token
        String tokenKey = userId + ":" + IdUtil.getSnowflakeNextIdStr();
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("tokenKey", tokenKey);
        String token = JWTUtil.createToken(map, tokenProperties.getSecret().getBytes());

        //填充LoginTokenDTO对象（本次登录的基本信息）
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = dateFormat.format(new Date());
        String clientIP = ServletUtil.getClientIP(MyServletUtil.getRequest());
        LoginTokenDTO loginTokenDTO = new LoginTokenDTO();
        loginTokenDTO.setTokenKey(tokenKey);
        loginTokenDTO.setCreteDate(currentDate);
        loginTokenDTO.setUpdateDate(currentDate);
        loginTokenDTO.setLoginIp(clientIP);

        //redis是否已有会话记录
        LoginSessionDTO loginSessionDTO = redisCache.getCacheObject(RedisConstants.LOGIN_SESSION + userId);
        if (loginSessionDTO == null) {
            loginSessionDTO = new LoginSessionDTO();
            BeanUtils.copyProperties(sysUser, loginSessionDTO);
            loginSessionDTO.setPermissions(permissions);
            loginSessionDTO.setRoles(roles);
            LinkedList<LoginTokenDTO> loginTokenDTOS = new LinkedList<>();
            loginSessionDTO.setLoginTokenDTOS(loginTokenDTOS);
        }

        //是否允许多端登录
        Boolean concurrent = tokenProperties.getConcurrent();
        if (concurrent) {
            if (loginSessionDTO.getLoginTokenDTOS().size() >= tokenProperties.getConcurrentMax()) {
                //超出最大允许登录数,删除最早登录的token
                Iterator<LoginTokenDTO> iterator = loginSessionDTO.getLoginTokenDTOS().iterator();
                iterator.next();
                iterator.remove();
            }
            loginSessionDTO.getLoginTokenDTOS().add(loginTokenDTO);

        } else {
            //禁止多端登录,始终保持只有一个token生效
            LinkedList<LoginTokenDTO> loginTokenDTOS = new LinkedList<>();
            loginTokenDTOS.add(loginTokenDTO);
            loginSessionDTO.setLoginTokenDTOS(loginTokenDTOS);

        }

        //缓存用户会话与登录信息
        redisCache.setCacheObject(RedisConstants.LOGIN_TOKEN + tokenKey, loginTokenDTO, tokenProperties.getExpireTime(), TimeUnit.MINUTES);
        redisCache.setCacheObject(RedisConstants.LOGIN_SESSION + userId, loginSessionDTO, tokenProperties.getExpireTime(), TimeUnit.MINUTES);
        return token;
    }

    /**
     * 校验token
     */
    public Boolean checkToken(String token) {
        String tokenKey = JWTUtil.parseToken(token).getPayload("tokenKey").toString();

        return true;
    }


    /**
     * 获取用户权限标识list
     *
     * @param userId
     * @return
     */
    public List<String> listPermissionByUserId(Long userId) {
        List<SysMenu> sysMenus = iSysMenuService.listPermissionByUserId(userId);
        return sysMenus.stream().map(SysMenu::getPerm).collect(Collectors.toList());
    }

    /**
     * 获取用户角色标识list
     *
     * @param userId
     * @return
     */
    public List<String> listRoleByUserId(Long userId) {
        List<SysRole> sysRoles = iSysRoleService.listRoleByUserId(userId);
        return sysRoles.stream().map(SysRole::getRoleKey).collect(Collectors.toList());
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
