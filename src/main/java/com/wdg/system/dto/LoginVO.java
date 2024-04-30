package com.wdg.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: wdg
 * @create: 2024-04-30 14:54
 */
@Data
public class LoginVO implements Serializable {

    private static final long serialVersionUID = -5594541720867152949L;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * token
     */
    private String token;
}
