package com.wdg.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdg.system.dto.SysUserDTO;
import com.wdg.system.entity.SysUser;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author wdg
 */
public interface ISysUserService extends IService<SysUser> {

    boolean insertSysUser(SysUserDTO sysUserDTO);

    boolean updateSysUser(SysUserDTO sysUserDTO);

    boolean deleteSysUser(Long userId);
}
