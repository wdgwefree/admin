package com.wdg.demo.controller;

import com.wdg.common.annotation.OpenAPI;
import com.wdg.common.dto.result.ApiResult;
import com.wdg.demo.service.UserRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo")
@RestController
public class MyController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @GetMapping("/test")
    @OpenAPI
    public ApiResult test() {
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this));
        return ApiResult.success();
    }

}
