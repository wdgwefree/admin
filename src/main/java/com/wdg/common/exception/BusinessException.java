package com.wdg.common.exception;

import com.wdg.common.constant.ResultCode;
import lombok.Getter;

/**
 * 用于手动抛出异常
 */
@Getter
public class BusinessException extends RuntimeException {

    private String code;
    private String msg;


    //用于指定错误信息
    public BusinessException(String msg) {
        this.code = ResultCode.ERROR.getCode();
        this.msg = msg;
    }

    //用于指定错误信息
    public BusinessException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //用于指定错误信息
    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }
    //用于指定错误信息
    public BusinessException(ResultCode resultCode,String info) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg()+"("+info+")";
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}