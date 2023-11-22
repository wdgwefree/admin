package com.wdg.common.exception;

import com.wdg.common.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 注解@RestControllerAdvice和@ExceptionHandler会捕获所有Rest接口的异常并封装成我们定义的HttpResult的结果集返回
 * 但是：处理不了拦截器里的异常!!!
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e) {
        // 自定义异常
        if (e instanceof BusinessException) {
            String msg = getExceptionMsg(e);
            log.error("BusinessException：" + e);
            return ApiResult.exception(msg);
        } else {
            log.error("Exception：" + e);
            return ApiResult.exception(e.toString());
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