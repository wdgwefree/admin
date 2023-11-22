package com.wdg.common.exception;

public class BusinessException extends RuntimeException {

    //用于指定错误信息
    public BusinessException(String message) {
        super(message);
    }
}