package com.wdg.common.Interceptor;

import com.wdg.common.annotation.OpenAPI;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Description 自定义拦截器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 每次请求之前触发的方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("请求路径:" + request.getServletPath() + "   进入拦截器===================preHandle");

        // 如果不是控制器方法，则直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        OpenAPI openAPI = method.getAnnotation(OpenAPI.class);
        //有注解@OpenAPI标注的方法，直接通过
        if (openAPI != null) {
            return true;
        }


        return false;

        //String token = request.getHeader(header);//获取请求头中的令牌
        //
        ////注销逻辑
        //if (request.getServletPath().contains("/system/logout")) {
        //    tokenService.delToken(response,token);
        //    return false;
        //}
        //return tokenService.verifyToken(response, token);
    }


}
