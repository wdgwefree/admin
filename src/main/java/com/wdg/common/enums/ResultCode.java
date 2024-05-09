package com.wdg.common.enums;

public enum ResultCode {

    SUCCESS("0", "操作成功"),
    ERROR("1000", "操作失败"),

    //用户端错误（一级宏观错误码）
    CLIENT_ERROR("A0001", "客户端异常"),


    //系统执行出错（一级宏观错误码）
    SYSTEM_ERROR("B0001", "系统异常"),


    //调用第三方服务出错（一级宏观错误码）
    THIRD_SERVICE_ERROR("C0001", "第三方服务异常"),


    NOT_LOGIN("", "未登录禁止访问"),
    ARGUMENT_EXCEPTION("", "请求参数异常"),

    //token相关异常
    TOKEN_EXCEPTION("", "token异常"),
    TOKEN_NOT_FOUND("", "token不存在"),
    TOKEN_EXPIRED("", "token已过期"),
    TOKEN_INVALID("", "token无效"),
    TOKEN_ERROR("", "token错误"),
    TOKEN_OVERDUE("", "token即将过期"),
    TOKEN_INVALID_REFRESH("", "刷新token无效"),


    //用户相关异常
    USER_EXCEPTION("", "用户相关异常"),
    USER_NOT_FOUND("", "用户不存在"),
    USER_PASSWORD_ERROR("", "用户密码错误"),
    USER_ACCOUNT_ERROR("", "用户账号错误"),
    USER_ACCOUNT_EXIST("", "用户账号已存在"),
    USER_ACCOUNT_NOT_EXIST("", "用户账号不存在"),
    USER_ACCOUNT_AlREADY_EXIST("", "用户账号已被删除"),
    USER_ACCOUNT_DISABLED("", "用户账号已禁用"),
    ;

    private String code;

    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}