package com.wdg.common.enums;

public enum ResultCode {

    SUCCESS("0", "操作成功"),
    ERROR("1000", "操作失败"),


    //用户端错误（一级宏观错误码）
    CLIENT_ERROR("A0001", "客户端异常"),

    //请求参数异常
    PARAM_ERROR("A0100", "请求参数异常"),
    TOKEN_NOT_FOUND("A0101", "token不存在"),
    TOKEN_EXPIRED("A0102", "token已过期"),
    TOKEN_INVALID("A0103", "token无效"),


    //二级宏观错误码
    LOGIN_EXCEPTION("A0200", "用户登录异常"),
    NOT_LOGIN("A0201", "用户未登录"),
    USER_ACCOUNT_NOT_FOUND("A0202", "用户账号不存在"),
    USER_ACCOUNT_AlREADY_EXIST("A0203", "用户账号已存在"),
    USER_ACCOUNT_DISABLED("A0204", "用户账号已禁用"),
    USER_ACCOUNT_AlREADY_DELETED("A0205", "用户账号已作废"),
    USER_ACCOUNT_LOCKED("A0206", "用户账号已锁定"),
    USER_LOGIN_OVER_MAX_LIMIT("A0207", "用户登录超出限制，用户被挤下线"),
    USER_PASSWORD_ERROR("A0210", "用户密码错误"),
    USER_PASSWORD_ERROR_OVER_MAX_LIMIT("A0211", "用户密码错误次数超限"),


    //系统执行出错（一级宏观错误码）
    SYSTEM_ERROR("B0001", "系统异常"),


    //调用第三方服务出错（一级宏观错误码）
    THIRD_SERVICE_ERROR("C0001", "第三方服务异常"),

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