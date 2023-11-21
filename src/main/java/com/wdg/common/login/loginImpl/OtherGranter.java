package com.wdg.common.login.loginImpl;

import com.wdg.common.constant.LoginConstants;
import com.wdg.common.login.UserGranter;
import org.springframework.stereotype.Component;

/**
 * @description: 其他方式登录
 * @author: wdg
 * @create: 2023-11-21 14:07
 */
@Component(LoginConstants.LOGIN_OTHER)
public class OtherGranter implements UserGranter {


    @Override
    public String grant(String type) {
        System.out.println("其他方式登录校验中...");
        return "其他方式登录";
    }
}
