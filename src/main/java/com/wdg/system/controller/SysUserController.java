package com.wdg.system.controller;

import com.wdg.common.dto.page.PageData;
import com.wdg.common.dto.result.ApiResult;
import com.wdg.common.utils.ValidatedGroup;
import com.wdg.system.dto.SysUserDTO;
import com.wdg.system.entity.SysUser;
import com.wdg.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {


    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 新增用户
     *
     * @param sysUserDTO
     * @return
     */
    @PostMapping("/add")
    public ApiResult add(@RequestBody @Validated({ValidatedGroup.Insert.class}) SysUserDTO sysUserDTO) {
        iSysUserService.add(sysUserDTO);
        return ApiResult.success();
    }

    @PostMapping("/pageSelect")
    public ApiResult pageSelect() {
        startPage();
        List<SysUser> list = iSysUserService.list();
        PageData pageData = getPageData(list);
        return ApiResult.success(pageData);
    }
}
