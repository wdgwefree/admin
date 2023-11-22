package com.wdg.common.exception;

import com.wdg.common.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 注解@RestControllerAdvice和@ExceptionHandler会捕获所有Rest接口的异常并封装成我们定义的HttpResult的结果集返回
 * 但是：处理不了拦截器里的异常!!!
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ApiResult handleException(BusinessException e) {
        log.error("自定义的业务异常[BusinessException]：" + e.getMsg());
        return ApiResult.businessException(e.toString());
    }

    /**
     * 处理异常
     */
    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e) {
        //处理参数异常
        // (嵌套对象字段缺失、校验不通过会走这里)
        if (e instanceof MethodArgumentNotValidException) {
            String msg = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage();
            log.error("参数异常[MethodArgumentNotValidException]：" + e.getMessage());
            return ApiResult.argumentException(msg);
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