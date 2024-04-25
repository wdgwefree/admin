package com.wdg.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.wdg.common.dto.result.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: wdg
 * @create: 2024-04-22 16:48
 */
@RestController
public class LoginController {



    @PostMapping("/login")
    public ApiResult login(){
        StpUtil.login(1000);
        return ApiResult.success();
    }
}
