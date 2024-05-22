package com.wdg.system.service;

import com.wdg.system.entity.SysMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    private ISysMenuService iSysMenuService;

    @Autowired
    private ISysRoleService iSysRoleService;

    @Test
    void listPermissionByUserId() {
        List<SysMenu> sysMenus = iSysMenuService.listPermissionByUserId(1L);
        System.out.println(sysMenus);

    }

    @Test
    void listRoleByUserId() {
    }
}