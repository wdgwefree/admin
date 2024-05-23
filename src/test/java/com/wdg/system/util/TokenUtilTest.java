package com.wdg.system.util;

import com.wdg.common.config.TokenProperties;
import com.wdg.common.constant.RedisConstants;
import com.wdg.common.util.RedisCache;
import com.wdg.system.dto.LoginSessionDTO;
import com.wdg.system.service.ISysMenuService;
import com.wdg.system.service.ISysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TokenUtilTest {

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private ISysMenuService iSysMenuService;

    @Autowired
    private ISysRoleService iSysRoleService;

    @Autowired
    private RedisCache redisCache;

    @Test
    void generateToken() {
        LoginSessionDTO loginSessionDTO = redisCache.getCacheObject(RedisConstants.LOGIN_SESSION + 1L);
        System.out.println(loginSessionDTO);
    }

    @Test
    void checkToken() {
    }

    @Test
    void listPermissionByUserId() {
    }

    @Test
    void listRoleByUserId() {
    }

    @Test
    void loginPasswordError() {
        Long userId = 1L;
        Long count = redisCache.incrementCacheObject(RedisConstants.LOGIN_ERROR + userId);
        System.out.println(count);
    }
}