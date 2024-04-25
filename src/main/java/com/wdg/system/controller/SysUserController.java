package com.wdg.system.controller;

import com.wdg.common.dto.result.ApiResult;
import com.wdg.common.utils.ValidatedGroup;
import com.wdg.system.dto.SysUserDTO;
import com.wdg.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysUser")
public class SysUserController {


    @Autowired
    private ISysUserService iSysUserService;

    @RequestMapping("/add")
    public ApiResult add(@Validated({ValidatedGroup.Insert.class}) SysUserDTO sysUserDTO) {
        return ApiResult.success();
    }
}
