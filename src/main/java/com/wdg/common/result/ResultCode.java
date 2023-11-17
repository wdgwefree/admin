package com.wdg.common.result;

public enum ResultCode {

    SUCCESS(200, "请求成功"),
    UNAUTHORIZED(401, "认证失败，请查询登录"),
    FORBIDDEN(403,"权限不足"),
    SERVER_ERROR(10000, "服务器异常"),
    AUTH_ERROR(10001, "认证失败"),
    PARAMS_ERROR(10002, "参数错误"),
    JSON_PARSE_ERROR(10003, "Json解析错误"),
    ILLEGAL_STRING(15001, "非法字符串"),
    UNKNOWN_ERROR(16000, "未知错误"),

    EXCEPTION_ERROR(-1, "运行时异常");


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