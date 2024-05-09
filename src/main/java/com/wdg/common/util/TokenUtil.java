package com.wdg.common.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWTUtil;
import com.wdg.common.config.TokenConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:token工具类
 */
public class TokenUtil {

    @Autowired
    private TokenConfig tokenConfig;


    /**
     * 生成token
     *
     * @return
     */
    public String generateToken() {

        String rKey = IdUtil.getSnowflakeNextIdStr();
        Map<String, Object> map = new HashMap<>();
        map.put("rKey", rKey);
        map.put("role", "admin");
        String token = JWTUtil.createToken(map, tokenConfig.getSecret().getBytes());
        return token;
    }
}
