package com.wdg.demo.controller;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWTUtil;
import com.wdg.common.result.ApiResult;
import com.wdg.demo.dto.ParamVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

        return ApiResult.success("123456");

    }

    @ApiOperation(value = "生成token")
    @GetMapping("/test2")
    public ApiResult test2() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "wdg");
        map.put("pwd", "123456");
        map.put("randomId", IdUtil.getSnowflakeNextIdStr());
        map.put("expire_time", System.currentTimeMillis() + 1000*30);
        String token = JWTUtil.createToken(map, "temp".getBytes());
        return ApiResult.success(token);
    }

}
