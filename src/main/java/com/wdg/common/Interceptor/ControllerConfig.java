package com.wdg.common.Interceptor;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Spring MVC的配置
 */
@Configuration
public class ControllerConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    /**
     * 添加拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //不需要拦截的接口在此处添加,或者在接口方法上用标注
        List<String> patterns = new ArrayList<>();
        patterns.add("/druid/**");
        patterns.add("/error");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**") //所有的请求都要拦截。
                .excludePathPatterns(patterns); //将不需要拦截的接口请求排除在外

        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin())).addPathPatterns("/**").excludePathPatterns("/login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*").allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置静态资源不被拦截
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
