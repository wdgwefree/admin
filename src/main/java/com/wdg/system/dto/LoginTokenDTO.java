package com.wdg.system.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * 登录token的Dto
 *
 * @author: wdg
 * @date: 2024/05/21
 */
@Data
public class LoginTokenDTO implements Serializable,Comparable<LoginTokenDTO> {
    private static final long serialVersionUID = 1L;

    /**
     * tokenKey
     */
    private String tokenKey;

    /**
     * 登录ip
     */
    private String LoginIp;

    /**
     * 创建时间
     */
    private String creteDate;

    /**
     * 创建时间
     */
    private String updateDate;


    @Override
    public int compareTo(@NotNull LoginTokenDTO o) {
        return this.creteDate.compareTo(o.creteDate);
    }
}
