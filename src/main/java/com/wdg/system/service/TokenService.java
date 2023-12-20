package com.wdg.system.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWTUtil;
import com.wdg.common.constant.RedisConstants;
import com.wdg.common.utils.RedisCache;
import com.wdg.system.dto.SysUserToken;
import com.wdg.system.entity.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: wdg
 * @create: 2023-12-20 16:49
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
        sysUserToken.setTokenStartTime(new Date());
        sysUserToken.setTokenEndDate(new Date(System.currentTimeMillis() + Long.parseLong(expireTime) * 60 * 1000));
        sysUserToken.setTokenKey(tokenKey);

        //存入redis
        redisCache.setCacheObject(RedisConstants.SYS_USER_TOKEN + tokenKey, sysUserToken, Integer.valueOf(expireTime), TimeUnit.MINUTES);

        return JWTUtil.createToken(map, secret.getBytes());
    }
}
