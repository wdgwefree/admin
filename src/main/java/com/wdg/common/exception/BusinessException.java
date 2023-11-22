package com.wdg.common.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private String code;
    private String msg;


    //用于指定错误信息
    public BusinessException(String msg) {
        this.msg = msg;
    }

    //用于指定错误信息
    public BusinessException(String code, String msg) {
        this.code=code;
        this.msg = msg;
    }
}