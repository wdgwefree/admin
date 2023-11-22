package com.wdg.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private String code;
    private String msg;


    //用于指定错误信息
    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    //用于指定错误信息
    public BusinessException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}