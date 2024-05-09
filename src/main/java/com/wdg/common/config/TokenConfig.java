package com.wdg.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 读取token相关配置
 */
@Component
@ConfigurationProperties(prefix = "token")
@Data
public class TokenConfig {
    /**
     * 令牌自定义标识
     */
    private String header = "Authorization";
    /**
     * 令牌密钥
     */
    private String secret = "abcdefghijklmnopqrstuvwxyzza";
    /**
     * 令牌有效期（默认30分钟）
     */
    private int expireTime = 30;
    /**
     * 距离令牌到期多久续期(默认20分钟)
     */
    private int alarmTime = 20;
    /**
     * 是否允许多设备同时登录(true:允许 false:不允许)
     */
    private boolean concurrent = true;
    /**
     * 同账号最大在线个数(如果concurrent为false，max_user将失效)
     */
    private int concurrentMax = 5;
}
