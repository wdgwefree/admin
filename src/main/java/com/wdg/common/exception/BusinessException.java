package com.wdg.common.exception;

import com.wdg.common.enums.ResultCode;
import lombok.Getter;

/**
 * 用于手动抛出异常
 */
@Getter
public class BusinessException extends RuntimeException {

    private Integer code;
    private String msg;


    //用于指定错误信息
    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    //用于指定错误信息
    public BusinessException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //用于指定错误信息
    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}