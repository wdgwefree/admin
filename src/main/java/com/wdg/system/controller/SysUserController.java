package com.wdg.system.controller;

import com.wdg.common.annotation.OpenAPI;
import com.wdg.common.result.ApiResult;
import com.wdg.common.utils.ValidatedGroup;
import com.wdg.system.entity.SysUser;
import com.wdg.system.service.ISysUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("system/sysUser")
public class SysUserController extends BaseController {


    @Resource
    private ISysUserService iSysUserService;

    /**
     * 新增系统用户
     *
     * @return
     */
    @PostMapping("/insertSysUser")
    @OpenAPI
    public ApiResult insertSysUser(@Validated({ValidatedGroup.Insert.class}) @RequestBody SysUser sysUser) {
        return toApiResult(iSysUserService.insertSysUser(sysUser));
    }

    /**
     * 更新系统用户
     *
     * @return
     */
    @PostMapping("/updateSysUser")
    public ApiResult updateSysUser(@RequestBody SysUser sysUser) {
        return toApiResult(iSysUserService.updateSysUser(sysUser));
    }
}
