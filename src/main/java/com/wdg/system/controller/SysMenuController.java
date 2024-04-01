package com.wdg.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.wdg.common.dto.result.ApiResult;
import com.wdg.system.dto.MenuDTO;
import com.wdg.system.entity.SysMenu;
import com.wdg.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private ISysMenuService iSysMenuService;

    @PostMapping("/queryList")
    public ApiResult queryList() {
        List<SysMenu> list = iSysMenuService.list();
        // 将菜单列表转换为树形结构
        List<MenuDTO> menuTree =buildMenuTree(list);
        // 返回树形结构的菜单列表
        return ApiResult.success(menuTree);    }


    /**
     * 构建菜单树
     */
    public List<MenuDTO> buildMenuTree(List<SysMenu> menuList) {
        // 创建一个 Map 用于存放菜单ID和对应的菜单对象
        Map<Long, MenuDTO> menuMap = new HashMap<>();
        for (SysMenu menu : menuList) {
            MenuDTO menuDTO = new MenuDTO();
            BeanUtil.copyProperties(menu, menuDTO);
            menuMap.put(menu.getMenuId(), menuDTO);
        }

        // 创建一个根菜单列表
        List<MenuDTO> rootMenus = new ArrayList<>();

        // 遍历菜单列表，将每个菜单添加到其父菜单的子菜单列表中
        for (SysMenu menu : menuList) {
            Long parentId = menu.getParentId();
            if (parentId == null || parentId == 0) {
                // 如果是根菜单，直接添加到根菜单列表中
                rootMenus.add(menuMap.get(menu.getMenuId()));
            } else {
                // 如果不是根菜单，则将其添加到父菜单的子菜单列表中
                MenuDTO parentMenu = menuMap.computeIfAbsent(parentId, k -> new MenuDTO());
                if (parentMenu.getSubMenus() == null) {
                    parentMenu.setSubMenus(new ArrayList<>());
                }
                parentMenu.getSubMenus().add(menuMap.get(menu.getMenuId()));
            }
        }

        return rootMenus;
    }

}
