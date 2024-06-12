package com.wdg.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 角色认证校验：必须具有指定角色标识才能进入该方法。
 * 只能标注在方法上
 *
 * @author wdg
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckRole {

    /**
     * 需要校验的角色标识[数组]
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
