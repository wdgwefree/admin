package com.wdg.demo.controller;

import cn.hutool.core.date.StopWatch;
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
    public ApiResult test1(@Validated @RequestBody ParamVO paramVO) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("一");
        //System.out.println(stopWatch.currentTaskName()+": ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(stopWatch.currentTaskName());
        stopWatch.stop();
        System.out.println(": "+stopWatch.getTotalTimeMillis());
        return ApiResult.success();
    }

}
