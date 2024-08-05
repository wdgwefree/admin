package com.wdg.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {

    /**
     * 两个相同请求之间的最小时间间隔，单位为毫秒
     * @return
     */
    int interval() default 3000;
}
