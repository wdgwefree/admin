package com.wdg.common.Interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import com.wdg.common.annotation.OpenAPI;
import com.wdg.common.result.ApiResult;
import com.wdg.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    @Value("${token.secret}")
    private String secret;

    /**
     * 在请求进入到Controller前进行拦截，有返回值。（返回true则将请求放行进入下一个拦截器，false则请求结束返回错误信息）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("请求路径:" + request.getServletPath() + "进入拦截器=======preHandle========");

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        OpenAPI openAPI = method.getAnnotation(OpenAPI.class);
        //有注解@OpenAPI标注的方法，直接通过
        if (openAPI != null) {
            return true;
        }

        String token = request.getHeader(header);//获取请求头中的令牌
        if (StrUtil.isEmpty(token)) {
            renderString(response, JSONUtil.toJsonStr(new ApiResult(ResultCode.TOKEN_EXCEPTION.getCode(), "token不存在")));
            return false;
        }
        boolean verify = JWTUtil.verify(token, secret.getBytes());
        if (!verify) {
            renderString(response, JSONUtil.toJsonStr(new ApiResult(ResultCode.TOKEN_EXCEPTION.getCode(), "token验证失败")));
            return false;
        }
        return true;
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param msg      待渲染的字符串
     */
    private void renderString(HttpServletResponse response, String msg) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
