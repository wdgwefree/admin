package com.wdg.system.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginBody {

    /**
     * 用户账号
     */
    @NotBlank(message = "userAccount不能为空")
    private String userAccount;

    /**
     * 用户密码
     */
    @NotBlank(message = "password不能为空")
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
