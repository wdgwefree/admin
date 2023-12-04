package com.wdg.common.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 添加拦截规则
 * @author: wdg
 * @create: 2023-11-24 17:01
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

        //不需要拦截的接口
        List<String> patterns = new ArrayList<>();
        patterns.add("/login");
        patterns.add("/wdg/test2");
        patterns.add("/baseFile/uploadFile");
        patterns.add("/baseFile/download");


        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**") //所有的请求都要拦截。
                .excludePathPatterns(patterns); //将不需要拦截的接口请求排除在外
    }
}
