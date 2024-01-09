package com.wdg.system.controller;


import com.wdg.common.annotation.OpenAPI;
import com.wdg.common.dto.result.ApiResult;
import com.wdg.system.dto.LoginBody;
import com.wdg.system.service.SysLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("system")
@Slf4j
public class SysLoginController extends BaseController {

    @Resource
    private SysLoginService sysLoginService;

    /**
     * 登录
     */
    @PostMapping("/login")
    @OpenAPI
    public ApiResult login(@RequestBody LoginBody loginBody) {
        String token = sysLoginService.login(loginBody);
        return ApiResult.success(token);
    }

    /**
     * 注销
     */
    @GetMapping ("/logout")
    public ApiResult logout() {
        return ApiResult.success();
    }
}
