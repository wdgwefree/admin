package com.wdg.common.enums;

public enum ResultCode {

    SUCCESS(0, "操作成功"),
    ERROR(1000, "操作失败"),

    ARGUMENT_EXCEPTION(10001, "请求参数异常"),

    //token相关异常
    TOKEN_EXCEPTION(10101,"token异常"),
    TOKEN_NOT_FOUND(10102,"token不存在"),
    TOKEN_EXPIRED(10103,"token已过期"),
    TOKEN_INVALID(10104,"token无效"),
    TOKEN_ERROR(10105,"token错误"),
    TOKEN_OVERDUE(10106,"token即将过期"),
    TOKEN_INVALID_REFRESH(10107,"刷新token无效"),


    //用户相关异常
    USER_EXCEPTION(10200, "用户相关异常"),
    USER_NOT_FOUND(10201, "用户不存在"),
    USER_PASSWORD_ERROR(10202, "用户密码错误"),
    USER_ACCOUNT_ERROR(10203, "用户账号错误"),
    USER_ACCOUNT_EXIST(10204, "用户账号已存在"),
    USER_ACCOUNT_NOT_EXIST(10205, "用户账号不存在"),
    USER_ACCOUNT_AlREADY_EXIST(10205, "用户账号已被删除"),
    USER_ACCOUNT_DISABLED(10206, "用户账号已禁用"),
    ;

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