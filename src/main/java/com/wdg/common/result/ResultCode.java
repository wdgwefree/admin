package com.wdg.common.result;

public enum ResultCode {

    SUCCESS(0, "操作成功"),
    ERROR(500, "操作失败"),

    EXCEPTION_ERROR(10001, "运行时异常"),
    BUSINESS_EXCEPTION(10002, "自定义业务异常"),
    ARGUMENT_EXCEPTION(10003, "请求参数异常"),
    TOKEN_EXCEPTION(10000,"token异常");




    private Integer code;

    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}