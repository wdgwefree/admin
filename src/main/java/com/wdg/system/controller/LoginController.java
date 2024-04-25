package com.wdg.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.wdg.common.dto.result.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/login")
    public ApiResult login() {
        StpUtil.login(1000);
        return ApiResult.success();
    }
}
