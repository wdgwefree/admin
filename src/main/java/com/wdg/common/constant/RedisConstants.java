package com.wdg.common.constant;

/**
 * redis 常量
 *
 * @author: wdg
 * @date: 2024/5/21
 **/
public class RedisConstants {


    /**
     * 登录token
     */
    public static final String LOGIN_TOKEN = "login:token:";


    /**
     * 登录session
     */
    public static final String LOGIN_SESSION = "login:session:";

    /**
     * 密码错误尝试次数
     */
    public static final String LOGIN_ERROR = "login:error:";


    /**
     * 密码错误锁定
     */
    public static final String LOGIN_LOCK = "login:lock:";

}
