package com.wdg.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录token的Dto
 *
 * @author: wdg
 * @date: 2024/05/21
 */
@Data
public class LoginTokenDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * token
     */
    private String token;

    /**
     * 创建时间
     */
    private Date creteDate;
}
