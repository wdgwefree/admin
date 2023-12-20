package com.wdg.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 标注不需要拦截的接口
 * @author: wdg
 * @create: 2023-12-18 15:27
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface  OpenAPI {
}
