package com.wdg.system.controller;

import com.wdg.common.dto.page.PageData;
import com.wdg.common.dto.result.ApiResult;
import com.wdg.common.utils.ValidatedGroup;
import com.wdg.system.dto.SysUserDTO;
import com.wdg.system.dto.LoginTokenDTO;
import com.wdg.system.entity.SysUser;
import com.wdg.system.service.ISysUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


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
    public ApiResult insertSysUser(@Validated({ValidatedGroup.Insert.class}) @RequestBody SysUserDTO sysUserDTO) {
        return toApiResult(iSysUserService.insertSysUser(sysUserDTO));
    }

    /**
     * 更新系统用户
     *
     * @return
     */
    @PostMapping("/update")
    public ApiResult updateSysUser(@RequestBody SysUserDTO sysUserDTO) {
        return toApiResult(iSysUserService.updateSysUser(sysUserDTO));
    }

    /**
     * 分页查询系统用户
     *
     * @return
     */
    @PostMapping("/pageSelect")
    public ApiResult selectSysUser() {
        LoginTokenDTO loginTokenDTO = getSysUserVo();
        System.out.println(loginTokenDTO);
        startPage();
        List<SysUser> list = iSysUserService.list();
        PageData pageData = getPageData(list);
        return ApiResult.success(pageData);
    }

    @PostMapping("/delete")
    public ApiResult deleteSysUser(@RequestBody Map<String, Object> map) {
        Long userId = Long.valueOf(map.get("userId").toString());
        return toApiResult(iSysUserService.deleteSysUser(userId));
    }

}
