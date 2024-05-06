package com.wdg.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdg.system.dto.MenuDTO;
import com.wdg.system.entity.SysMenu;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author wdg
 * @since 2024-04-22
 */
public interface ISysMenuService extends IService<SysMenu> {

    void add(MenuDTO menuDTO);

    void updateMenu(MenuDTO menuDTO);

    void deleteById(Long menuId);
}
