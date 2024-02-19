package com.wdg.demo.login;

/**
 * 抽象策略接口
 */
public interface UserGranter {

    /**
     * 授权
     */
    String grant(String type);

}
