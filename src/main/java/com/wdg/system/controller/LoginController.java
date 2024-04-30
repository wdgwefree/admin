package com.wdg.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.wdg.common.annotation.OpenAPI;
import com.wdg.common.dto.result.ApiResult;
import com.wdg.system.dto.LoginDTO;
import com.wdg.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @OpenAPI
    public ApiResult login(@RequestBody LoginDTO loginDTO) {
        String tokenValue = loginService.login(loginDTO);
        return ApiResult.success(tokenValue);
    }


    @GetMapping("/logout")
    public ApiResult logout() {
        StpUtil.logout();
        return ApiResult.success();
    }

    /**
     * 获取当前登录的用户信息
     */
    @GetMapping("/getLoginInfo")
    public ApiResult getLoginInfo() {
        return ApiResult.success();
    }
}
