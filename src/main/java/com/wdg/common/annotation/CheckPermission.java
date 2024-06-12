package com.wdg.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验注解:必须具有指定权限标识才能进入该方法。
 * 只能标注在方法上
 *
 * @author wdg
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermission {


    /**
     * 需要校验的权限[数组]
     *
     * @return
     */
    String[] value() default {};


    /**
     * 验证模式：AND | OR，默认AND
     *
     * @return
     */
    CheckMode mode() default CheckMode.AND;

}
