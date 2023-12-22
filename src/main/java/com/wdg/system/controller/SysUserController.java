package com.wdg.system.controller;

import com.wdg.common.dto.PageData;
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
import java.util.List;


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
    @PostMapping("/insert")
    public ApiResult insertSysUser(@Validated({ValidatedGroup.Insert.class}) @RequestBody SysUser sysUser) {
        return toApiResult(iSysUserService.insertSysUser(sysUser));
    }

    /**
     * 更新系统用户
     *
     * @return
     */
    @PostMapping("/update")
    public ApiResult updateSysUser(@RequestBody SysUser sysUser) {
        return toApiResult(iSysUserService.updateSysUser(sysUser));
    }

    //分页查询系统用户
    @PostMapping("/pageSelect")
    public ApiResult selectSysUser() {
        startPage();
        List<SysUser> list = iSysUserService.list();
        PageData pageData = getPageData(list);
        return ApiResult.success(pageData);
    }

}
