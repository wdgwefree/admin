package com.wdg.common.Interceptor;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
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

        String token = request.getHeader("token");//获取请求头中的令牌
        boolean verify = JWTUtil.verify(token, "temp".getBytes());
        System.out.println("token验证结果:"+verify);
        JWT jwt = JWTUtil.parseToken(token);
        JWTPayload payload = jwt.getPayload();
        System.out.println("jwt:"+payload);
        System.out.println(System.currentTimeMillis());
        if (verify){
            return true;
        }else{
            return false;
        }
    }
}
