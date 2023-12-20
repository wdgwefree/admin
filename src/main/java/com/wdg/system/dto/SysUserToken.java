package com.wdg.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wdg.system.entity.SysUser;
import lombok.Data;

import java.util.Date;

/**
 * @description: 存入Redis的实体
 * @author: wdg
 * @create: 2023-12-18 17:34
 */
@Data
public class SysUserToken extends SysUser {

    //token开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tokenStartTime;

    //token过期时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tokenEndDate;
    
    private String tokenKey;

}
