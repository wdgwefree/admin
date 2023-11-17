package com.wdg.common.result;

import lombok.Data;

/**
 * Restful接口返回统一格式数据
 */

@Data
public class ApiResult {

    /**
     * 响应状态码，如成功200
     */
    private Integer code;

    /**
     * 对错误的具体解释
     */
    private String msg;

    /**
     * 是否响应成功 成功true,失败false
     */
    private Boolean success;

    /**
     * 返回的结果包装在value中，value可以是单个对象
     */
    private Object data;


    private ApiResult() {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.success = true;

    }

    private ApiResult(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.success = true;
        this.data = data;
    }


    private ApiResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.success = false;
    }

    private ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.success = false;
    }


    /**
     * 通用返回成功（没有返回结果,即data为null）
     *
     * @return
     */
    public static ApiResult success() {
        return new ApiResult();
    }


    /**
     * 通用返回成功（有反回结果）
     *
     * @param data
     * @return
     */
    public static ApiResult success(Object data) {
        return new ApiResult(data);
    }


    /**
     * 通用返回失败
     *
     * @param resultCode
     * @return
     */
    public static ApiResult error(ResultCode resultCode) {
        return new ApiResult(resultCode);
    }


    /**
     * 异常返回失败
     *
     * @param msg
     * @return
     */
    public static ApiResult exception(String msg) {
        return new ApiResult(ResultCode.EXCEPTION_ERROR.getCode(), msg);
    }

}
