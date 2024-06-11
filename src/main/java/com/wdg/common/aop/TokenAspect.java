package com.wdg.common.aop;

import cn.hutool.jwt.JWTUtil;
import com.wdg.common.annotation.CheckMode;
import com.wdg.common.annotation.CheckPermission;
import com.wdg.common.config.TokenProperties;
import com.wdg.common.constant.RedisConstants;
import com.wdg.common.constant.ResultCode;
import com.wdg.common.exception.BusinessException;
import com.wdg.common.util.MyServletUtil;
import com.wdg.common.util.RedisCache;
import com.wdg.system.dto.LoginSessionDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class TokenAspect {

    @Autowired
    private TokenProperties tokenProperties;


    @Autowired
    private RedisCache redisCache;

    //定义切点表达式
    @Pointcut("@annotation(com.wdg.common.annotation.CheckPermission)")
    public void permissionCut() {
    }

    @Around("permissionCut()")
    public void before(ProceedingJoinPoint proceedingJoinPoint) {
        String token = MyServletUtil.getRequest().getHeader(tokenProperties.getHeader());
        String userId = JWTUtil.parseToken(token).getPayload("userId").toString();
        LoginSessionDTO loginSessionDTO = redisCache.getCacheObject(RedisConstants.LOGIN_SESSION + userId);
        List<String> permissions = loginSessionDTO.getPermissions();
        List<String> roles = loginSessionDTO.getRoles();
        //具有超级管理员角色，直接放行
        if (roles.contains("admin")) {
            return;
        }
        if (permissions.size() == 0) {
            throw new BusinessException(ResultCode.PERMISSION_NOT_FOUND);
        }

        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        CheckPermission checkPermission = method.getAnnotation(CheckPermission.class);
        //获取注解的值
        String[] value = checkPermission.value();
        CheckMode mode = checkPermission.mode();
        switch (mode) {
            case AND:
                for (String permission : value) {
                    if (!permissions.contains(permission)) {
                        throw new BusinessException(ResultCode.PERMISSION_NOT_ENOUGH);
                    }
                }
                break;
            case OR:
                for (String permission : value) {
                    if (permissions.contains(permission)) {
                        return;
                    }
                }
                throw new BusinessException(ResultCode.PERMISSION_NOT_ENOUGH);
        }


    }
}
