package com.wdg.system.dto;

import lombok.Data;

@Data
public class LoginBody {

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;

}
