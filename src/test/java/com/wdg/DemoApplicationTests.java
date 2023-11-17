package com.wdg;

import com.wdg.common.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    private RedisCache redisCache;

    @Test
    void contextLoads() {
        redisCache.setCacheObject("temp","111");
    }

}
