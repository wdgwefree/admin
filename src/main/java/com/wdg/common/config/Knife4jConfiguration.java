package com.wdg.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @description: Knife4jConfig
 * @author: wdg
 * @create: 2023-11-22 16:25
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean
    public Docket api1() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                //分组名称
                .groupName("分组1")
                .apiInfo(new ApiInfoBuilder()
                        .title("wdg的Demo")
                        .description("Demo接口文档")
                        .termsOfServiceUrl("http://localhost/")
                        .contact(new Contact("wdg", "", ""))
                        .version("1.0")
                        .build())
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.wdg.demo.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean
    public Docket api2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                //分组名称
                .groupName("分组2")
                .apiInfo(new ApiInfoBuilder()
                        .title("wdg的Demo")
                        .description("Demo接口文档")
                        .termsOfServiceUrl("http://localhost/")
                        .contact(new Contact("wdg", "", ""))
                        .version("1.0")
                        .build())
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.wdg.demo.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

}