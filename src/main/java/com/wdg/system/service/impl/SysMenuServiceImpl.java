package com.wdg.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wdg.common.constant.StatusConstants;
import com.wdg.system.dto.MenuDTO;
import com.wdg.system.entity.SysMenu;
import com.wdg.system.mapper.SysMenuMapper;
import com.wdg.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author wdg
 * @since 2024-04-22
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public void add(MenuDTO menuDTO) {
        SysMenu sysMenu = new SysMenu();
        BeanUtil.copyProperties(menuDTO, sysMenu);
        this.save(sysMenu);
    }

    @Override
    public void updateMenu(MenuDTO menuDTO) {
        SysMenu sysMenu = new SysMenu();
        BeanUtil.copyProperties(menuDTO, sysMenu);
        this.updateById(sysMenu);
    }

    @Override
    public void deleteById(Long menuId) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuId(menuId);
        sysMenu.setStatus(StatusConstants.NOT_EXIST);
        this.updateById(sysMenu);
    }

    @Override
    public List<SysMenu> listPermissionByUserId(Long userId) {
        List<SysMenu> sysMenus = sysMenuMapper.listPermissionByUserId(userId);
        return sysMenus;
    }


}
