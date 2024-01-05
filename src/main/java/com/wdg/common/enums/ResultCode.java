package com.wdg.common.enums;

public enum ResultCode {

    SUCCESS(0, "操作成功"),
    ERROR(10000, "操作失败"),
    EXCEPTION_ERROR(10001, "运行时异常"),
    BUSINESS_EXCEPTION(10002, "自定义业务异常"),
    ARGUMENT_EXCEPTION(10003, "请求参数异常"),
    TOKEN_EXCEPTION(10000,"token异常"),


    //用户相关异常
    USER_EXCEPTION(10100, "用户相关异常"),
    USER_NOT_FOUND(10101, "用户不存在"),
    USER_PASSWORD_ERROR(10102, "用户密码错误"),
    USER_ACCOUNT_ERROR(10103, "用户账号错误"),
    USER_ACCOUNT_EXIST(10104, "用户账号已存在"),
    USER_ACCOUNT_NOT_EXIST(10105, "用户账号不存在"),
    USER_ACCOUNT_DISABLED(10106, "用户账号已禁用"),

    EXCEPTION(10101110, "222");


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