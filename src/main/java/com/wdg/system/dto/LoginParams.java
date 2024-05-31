package com.wdg.system.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录参数
 *
 * @author: wdg
 **/
@Data
public class LoginParams {

    @NotBlank(message = "userAccount不能为空")
    private String userAccount;

    @NotBlank(message = "password不能为空")
    private String password;

    private String code;

    private String loginType;
}
