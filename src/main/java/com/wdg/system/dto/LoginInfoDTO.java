package com.wdg.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 登录的用户信息
 *
 * @author: wdg
 */
@Data
public class LoginInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户类型（00系统用户 01注册用户）
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

    /**
     * 头像路径
     */
    private String avatar;

    /**
     * 用户权限集合
     */
    private List<String> permissions;

    /**
     * 用户角色集合
     */
    private List<String> roles;
}
