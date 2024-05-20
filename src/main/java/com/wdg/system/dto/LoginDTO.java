package com.wdg.system.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description: 登录参数
 * @author: wdg
 * @date: 2024/5/17
**/
@Data
public class LoginDTO {

    @NotBlank(message = "userAccount不能为空")
    private String userAccount;

    @NotBlank(message = "password不能为空")
    private String password;

    private String code;

    private String loginType;
}
