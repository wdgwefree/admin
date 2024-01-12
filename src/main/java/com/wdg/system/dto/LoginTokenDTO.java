package com.wdg.system.dto;

import com.wdg.system.entity.SysUser;
import lombok.Data;


@Data
public class LoginTokenDTO extends SysUser {

    /**
     * tokenKey
     */
    private String tokenKey;

    /**
     * 登录ip
     */
    private String loginIp;

}
