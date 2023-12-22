package com.wdg.common.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Spring MVC的配置
 */
@Component
public class ControllerConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    /**
     * 添加拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //不需要拦截的接口在此处添加,或者在接口方法上用@OpenAPI标注
        List<String> patterns = new ArrayList<>();
        patterns.add("/druid/**");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**") //所有的请求都要拦截。
                .excludePathPatterns(patterns); //将不需要拦截的接口请求排除在外
    }
}
