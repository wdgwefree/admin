package com.wdg.demo.controller;

import com.wdg.common.result.ApiResult;
import com.wdg.demo.dto.ParamVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "demo测试模块")
@RequestMapping("/wdg")
@RestController
public class MyController {

    @ApiOperation(value = "这个是测试test接口")
    @PostMapping("/test1")
    public ApiResult test1(@Validated @RequestBody ParamVO paramVO){
        return ApiResult.success();
    }

}
