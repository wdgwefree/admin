package com.wdg;

import cn.hutool.core.util.HexUtil;
import com.wdg.common.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigInteger;

@SpringBootTest
class AdminApplicationTests {

    @Resource
    private RedisCache redisCache;

    @Test
    void contextLoads() {
        String s = HexUtil.encodeHexStr("715111757484677356533075522481757640017785754675");
        BigInteger bigInteger = new BigInteger("715111757484677356533075522481757640017785754675");
        System.out.println(bigInteger.toString(16).toUpperCase());
    }

}
