package com.wdg.common.login.loginImpl;

import com.wdg.common.constant.LoginConstants;
import com.wdg.common.login.UserGranter;
import org.springframework.stereotype.Component;

/**
 * @description: 账号密码登录
 * @author: wdg
 * @create: 2023-11-21 14:00
 */
@Component(LoginConstants.LOGIN_ACCOUNT_PASSWORD)
public class AccountGranter implements UserGranter {

    @Override
    public String grant(String type) {
        System.out.println("账号密码登录校验中...");
        return "账号密码登录";
    }
}
