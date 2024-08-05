package com.wdg.common.Interceptor;

import com.wdg.common.annotation.OpenAPI;
import com.wdg.common.annotation.RepeatSubmit;
import com.wdg.common.config.TokenProperties;
import com.wdg.common.constant.RedisConstants;
import com.wdg.common.constant.ResultCode;
import com.wdg.common.exception.BusinessException;
import com.wdg.common.util.RedisCache;
import com.wdg.system.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 全局拦截器（token拦截、重复提交拦截）
 *
 * @author wdg
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private RedisCache redisCache;

    /**
     * 每次请求之前触发的方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("请求路径:" + request.getServletPath() + "   进入全局拦截器");

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
        String token = request.getHeader(tokenProperties.getHeader());//获取请求头中的令牌
        //校验token
        tokenUtil.verifyToken(token, method);

        //检测是否开启防止接口重复提交
        RepeatSubmit repeatSubmit = method.getAnnotation(RepeatSubmit.class);
        if (repeatSubmit != null) {
            String key = RedisConstants.REPEAT_SUBMIT + token + request.getServletPath();
            if(redisCache.hasKey(key)){
                throw new BusinessException(ResultCode.REPEAT_SUBMIT);
            }else{
                int interval = repeatSubmit.interval();
                redisCache.setCacheObject(key, 1, interval, TimeUnit.MILLISECONDS);
            }
        }
        return true;
    }

}
