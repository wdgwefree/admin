package com.wdg.system.controller;

import com.wdg.common.annotation.OpenAPI;
import com.wdg.common.dto.result.ApiResult;
import com.wdg.system.dto.LoginInfoDTO;
import com.wdg.system.dto.LoginParams;
import com.wdg.system.service.LoginService;
import com.wdg.system.util.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器类
 *
 * @author wdg
 */
@RestController
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @OpenAPI
    public ApiResult login(@RequestBody LoginParams loginParams) {
        String tokenValue = loginService.login(loginParams);
        return ApiResult.success(tokenValue);
    }

    @GetMapping("/logout")
    public ApiResult logout() {
        return ApiResult.success("注销成功");
    }

    /**
     * 获取当前登录的用户信息
     */
    @GetMapping("/getLoginInfo")
    public ApiResult getLoginInfo() {
        LoginInfoDTO loginInfoDTO = getLoginInfoDTO();
        return ApiResult.success(loginInfoDTO);
    }


    /**
     * 开发阶段的方法
     *
     * @param pwd
     * @return
     */
    @PostMapping("/getAdminPwd")
    @OpenAPI
    public ApiResult getEncryptPwd(String pwd) {
        return ApiResult.success(RsaUtil.encryptByPublicKey(pwd));
    }
}
