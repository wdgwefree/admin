package com.wdg.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 权限校验注解
 * 可标注在方法、类上（效果等同于标注在此类的所有方法上）
 */
@Target({ElementType.METHOD, ElementType.TYPE})
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
