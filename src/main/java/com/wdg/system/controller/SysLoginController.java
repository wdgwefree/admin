package com.wdg.system.controller;


import com.wdg.common.annotation.OpenAPI;
import com.wdg.common.result.ApiResult;
import com.wdg.system.dto.LoginBody;
import com.wdg.system.service.SysLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("system")
public class SysLoginController extends BaseController {

    @Resource
    private SysLoginService sysLoginService;

    /**
     * 登录系统
     */
    @PostMapping("/login")
    @OpenAPI
    public ApiResult login(@RequestBody LoginBody loginBody) {
        String token = sysLoginService.login(loginBody);
        return ApiResult.success(token);
    }
}
