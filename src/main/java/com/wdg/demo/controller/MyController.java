package com.wdg.demo.controller;

import com.wdg.common.result.ApiResult;
import com.wdg.demo.dto.ParamVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: test
 * @author: wdg
 * @create: 2023-11-22 14:34
 */
@RequestMapping("/wdg")
@RestController
public class MyController {

    @PostMapping("/test1")
    public ApiResult test1(@Validated @RequestBody ParamVO paramVO){
        return ApiResult.success();
    }

}
