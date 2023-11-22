package com.wdg.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * Restful接口返回统一格式数据
 */

@Data
public class ApiResult implements Serializable {

    private static final long serialVersionUID = -7916154205057820567L;


    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String msg;

    /**
     * 返回的结果包装在data中，data可以是单个对象
     */
    private Object data;


    private ApiResult() {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
    }

    private ApiResult(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    private ApiResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    private ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
     * @param
     * @return
     */
    public static ApiResult error() {
        return new ApiResult(ResultCode.ERROR.getCode(),ResultCode.ERROR.getMsg());
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
     * 异常返回
     *
     * @param msg
     * @return
     */
    public static ApiResult exception(String msg) {
        return new ApiResult(ResultCode.EXCEPTION_ERROR.getCode(), msg);
    }


    /**
     * 自定义业务异常返回
     *
     * @param msg
     * @return
     */
    public static ApiResult businessException(String msg) {
        return new ApiResult(ResultCode.BUSINESS_EXCEPTION.getCode(), msg);
    }

    /**
     * 参数异常返回
     *
     * @param msg
     * @return
     */
    public static ApiResult argumentException(String msg) {
        return new ApiResult(ResultCode.ARGUMENT_EXCEPTION.getCode(), msg);
    }
}
