package com.wdg.system.controller;

import com.wdg.common.dto.result.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description:
 * @author: wdg
 * @create: 2024-04-22 16:48
 */
public class LoginController {



    @PostMapping("/login")
    public ApiResult login(){
        return ApiResult.success();
    }
}
