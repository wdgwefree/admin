package com.wdg.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdg.system.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author wdg
 * @since 2024-04-22
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> listRoleByUserId(Long userId);

}
