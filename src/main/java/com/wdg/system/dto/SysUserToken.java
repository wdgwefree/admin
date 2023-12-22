package com.wdg.system.dto;

import com.wdg.system.entity.SysUser;
import lombok.Data;

/**
 * @description: 存入Redis的实体
 * @author: wdg
 * @create: 2023-12-18 17:34
 */
@Data
public class SysUserToken extends SysUser {

    /**
     * tokenKey
     */
    private String tokenKey;

    /**
     * 登录ip
     */
    private String loginIp;

}
