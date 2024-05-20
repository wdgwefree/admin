package com.wdg.common.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWTUtil;
import com.wdg.common.config.TokenProperties;
import com.wdg.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: token工具类
 * @author: wdg
 * @date: 2024/5/17
 **/
public class TokenUtil {

    @Autowired
    private TokenProperties tokenProperties;

    /**
     * 生成token
     *
     * @return
     */
    public String generateToken(SysUser sysUser) {
        String rKey = IdUtil.getSnowflakeNextIdStr();
        Map<String, Object> map = new HashMap<>();
        map.put("rKey", rKey);
        map.put("role", "admin");
        String token = JWTUtil.createToken(map, tokenProperties.getSecret().getBytes());
        return token;
    }
}
