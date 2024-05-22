package com.wdg.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdg.system.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author wdg
 * @since 2024-04-22
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> listPermissionByUserId(Long userId);
}
