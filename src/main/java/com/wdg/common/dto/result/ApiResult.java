package com.wdg.common.dto.result;

import com.wdg.common.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * Restful接口返回统一格式数据
 */

@Data
public class ApiResult implements Serializable {

    private static final long serialVersionUID = 1L;

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
        this.data = null;
    }

    private ApiResult(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    public ApiResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = null;
    }

    public ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }


    /**
     * 通用返回成功
     *
     * @return
     */
    public static ApiResult success() {
        return new ApiResult();
    }


    /**
     * 通用返回成功
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
     * @return
     */
    public static ApiResult error() {
        return new ApiResult(ResultCode.ERROR);
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
     * @param resultCode
     * @return
     */
    public static ApiResult exception(ResultCode resultCode) {
        return new ApiResult(resultCode);
    }


    /**
     * 异常返回
     *
     * @param code
     * @param msg
     * @return
     */
    public static ApiResult exception(Integer code, String msg) {
        return new ApiResult(code, msg);
    }


    /**
     * 异常返回
     *
     * @param msg
     * @return
     */
    public static ApiResult exception(String msg) {
        return new ApiResult(ResultCode.ERROR.getCode(), msg);
    }


    /**
     * 参数异常
     *
     * @param msg
     * @return
     */
    public static ApiResult paramsException(String msg) {
        return new ApiResult(ResultCode.ARGUMENT_EXCEPTION.getCode(), msg);
    }
}
