package com.wdg.system.controller;

import com.wdg.common.dto.page.PageData;
import com.wdg.common.dto.result.ApiResult;
import com.wdg.common.utils.ValidatedGroup;
import com.wdg.system.dto.MenuDTO;
import com.wdg.system.entity.SysMenu;
import com.wdg.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author wdg
 * @since 2024-04-22
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseController {


    @Autowired
    private ISysMenuService iSysMenuService;

    /**
     * 新增菜单/权限
     */
    @PostMapping("/add")
    public ApiResult add(@RequestBody MenuDTO menuDTO) {
        iSysMenuService.add(menuDTO);
        return ApiResult.success();
    }

    /**
     * 更新菜单
     * @param menuDTO
     * @return
     */
    @PostMapping("/update")
    public ApiResult update(@RequestBody @Validated({ValidatedGroup.Update.class}) MenuDTO menuDTO) {
        iSysMenuService.updateMenu(menuDTO);
        return ApiResult.success();
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @PostMapping("/deleteById")
    public ApiResult deleteById(@RequestBody Long menuId) {
        iSysMenuService.deleteById(menuId);
        return ApiResult.success();
    }


    /**
     * 分页查询
     * @return
     */
    @PostMapping("/pageSelect")
    public ApiResult pageSelect() {
        startPage();
        List<SysMenu> list = iSysMenuService.list();
        PageData pageData = getPageData(list);
        return ApiResult.success(pageData);
    }

}
