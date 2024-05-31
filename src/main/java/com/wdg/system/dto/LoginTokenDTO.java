package com.wdg.system.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * 登录token的Dto
 *
 * @author: wdg
 */
@Data
public class LoginTokenDTO implements Serializable, Comparable<LoginTokenDTO> {
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

    @Override
    public int compareTo(@NotNull LoginTokenDTO o) {
        return this.creteDate.compareTo(o.creteDate);
    }
}
