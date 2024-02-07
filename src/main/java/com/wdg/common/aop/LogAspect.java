package com.wdg.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LogAspect {


    //定义切点表达式
    @Pointcut("@annotation(com.wdg.common.annotation.LogInOut)")
    public void LogInOut() {
    }


    //@Before("LogInOut()")
    //public void logMethodEntry(JoinPoint joinPoint) {
    //    String methodName = joinPoint.getSignature().toShortString();
    //    Object[] args = joinPoint.getArgs();
    //    log.info("方法: {} 入参: {}", methodName, args);
    //}

    @AfterReturning(pointcut = "LogInOut()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        StringBuffer infoStr = new StringBuffer();

        StringBuffer argStr = new StringBuffer("参数: ");
        for (Object arg : joinPoint.getArgs()) {
            argStr.append(arg.toString() + "  ");
        }
        infoStr.append("方法: " + joinPoint.getSignature().toShortString() + argStr + " 返回值: " + result);

        log.info(infoStr.toString());
    }

}
