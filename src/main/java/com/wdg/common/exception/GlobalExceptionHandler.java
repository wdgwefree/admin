package com.wdg.common.exception;

import com.wdg.common.dto.result.ApiResult;
import com.wdg.common.constant.ResultCode;
import com.wdg.common.util.MyServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器，专门用于处理 REST API 的异常情况
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ApiResult handleException(BusinessException e) {
        //e.printStackTrace();
        HttpServletRequest request = MyServletUtil.getRequest();
        String servletPath = request.getServletPath();
        log.error("请求 " + servletPath + " : [code：{" + e.getCode() + "}，msg：{" + e.getMsg() + "}]");
        return ApiResult.exception(e.getCode(), e.getMsg());
    }

    /**
     * 处理异常
     */
    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e) {
        //e.printStackTrace();
        //处理参数异常
        // (嵌套对象字段缺失、校验不通过会走这里)
        if (e instanceof MethodArgumentNotValidException) {
            String msg = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage();
            log.error("参数异常[MethodArgumentNotValidException]：" + e.getMessage());
            return ApiResult.exception(ResultCode.PARAM_ERROR.getCode(), msg);
        } else if (e instanceof HttpMessageNotReadableException) {
            log.error("参数异常[HttpMessageNotReadableException]：" + e.getMessage());
            return ApiResult.exception(ResultCode.PARAM_ERROR.getCode(), "请求参数类型不匹配");
        } else if (e instanceof MissingServletRequestParameterException) {
            log.error("参数异常[MissingServletRequestParameterException]：" + e.getMessage());
            return ApiResult.exception(ResultCode.PARAM_ERROR.getCode(), getExceptionMsg(e));
        } else {
            log.error("异常[Exception]：" + e.getMessage());
            return ApiResult.exception(getExceptionMsg(e));
        }
    }


    /**
     * 获取错误信息
     *
     * @param e
     * @return
     */
    private String getExceptionMsg(Exception e) {
        String message = e.getMessage();
        try {
            int startIdx = message.indexOf(": ");
            if (startIdx < 0) {
                startIdx = 0;
            }
            int endIdx = message.indexOf(", ");
            if (endIdx < 0) {
                endIdx = message.length();
            }
            message = message.substring(startIdx, endIdx);
            return message;
        } catch (Throwable throwable) {
            return message;
        }
    }
}