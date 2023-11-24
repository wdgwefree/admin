package com.wdg.common.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: token拦截器
 * @author: wdg
 * @create: 2023-11-24 16:56
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 在请求进入到Controller进行拦截，有返回值。（返回true则将请求放行进入Controller控制层，false则请求结束返回错误信息）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("进入拦截器=======执行前========");
        return true;
    }
}
