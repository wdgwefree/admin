package com.wdg;

import com.wdg.common.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @description: 项目启动前运行
 * @author: wdg
 * @create: 2023-11-20 17:01
 */
@Component
public class ApplicationInit implements ApplicationRunner {

    @Autowired
    private RedisCache redisCache;

    private StringBuilder msgStr = new StringBuilder();


    @Override
    public void run(ApplicationArguments args) throws Exception {
        msgStr.append("中间件测试开始\n");
        redisTest();
        mysqlTest();
        System.out.println(msgStr.toString());
    }


    /**
     * Redis连接测试
     */
    private void redisTest() {
        String pong = redisCache.ping();
        if ("PONG".equals(pong)) {
            msgStr.append("redis连接   √\n");
        } else {
            msgStr.append("redis连接   ×\n");
        }
    }

    /**
     * mysql连接测试
     */
    private void mysqlTest() {

    }

}
