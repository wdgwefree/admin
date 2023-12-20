package com.wdg;

import com.wdg.common.utils.MinioUtil;
import com.wdg.common.utils.RedisCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 项目启动前运行
 * @author: wdg
 * @create: 2023-11-20 17:01
 */
@Component
public class ApplicationInit implements ApplicationRunner {

    @Resource
    private RedisCache redisCache;

    @Resource
    private MinioUtil minioUtil;

    @Value("${minio.bucketName}")
    private String bucketName;

    private StringBuilder msgStr = new StringBuilder();


    @Override
    public void run(ApplicationArguments args) throws Exception {
        msgStr.append("中间件测试开始\n");
        redisTest();
        mysqlTest();
        minioTest();
        System.out.println(msgStr.toString());
    }


    /**
     * Redis测试
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
     * mysql测试
     */
    private void mysqlTest() {

    }

    /**
     * minio测试
     */
    private void minioTest() {
        try {
            boolean data = minioUtil.bucketExists(bucketName);
            msgStr.append("minio连接   √\n");
        } catch (Exception e) {
            msgStr.append("minio连接   ×\n");
        }
    }

}
