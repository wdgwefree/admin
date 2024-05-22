package com.wdg.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdg.system.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author wdg
 * @since 2024-04-22
 */
public interface ISysRoleService extends IService<SysRole> {

    List<SysRole> listRoleByUserId(Long userId);

}
