package com.wdg.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: token属性配置
 * @author: wdg
 * @date: 2024/5/17
 **/
@Component
@ConfigurationProperties(prefix = "token")
@Data
public class TokenProperties {
    /**
     * 令牌自定义标识
     */
    private String header = "Authorization";
    /**
     * 令牌密钥
     */
    private String secret = "//**/x";
    /**
     * 令牌有效期（默认30分钟）
     */
    private Integer expireTime = 30;
    /**
     * 距离令牌到期多久续期(默认10分钟)
     */
    private Integer alarmTime = 10;
    /**
     * 是否允许多设备同时登录(true:允许 false:不允许)
     */
    private Boolean concurrent = true;
    /**
     * 同账号最大在线个数(如果concurrent为false，max_user将失效)
     */
    private Integer concurrentMax = 5;
    /**
     * 登录失败次数
     */
    private Integer pwdErrorMax = 5;
    /**
     * 登录失败时间范围（单位分钟）
     */
    private Integer pwdErrorTime = 5;
    /**
     * 登录失败锁定时间（单位分钟）
     */
    private Integer pwdErrorLockTime = 10;

}
