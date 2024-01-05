package com.wdg.system.service;

import com.wdg.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author wdg
 */
public interface ISysUserService extends IService<SysUser> {

    boolean insertSysUser(SysUser sysUser);

    boolean updateSysUser(SysUser sysUser);

    boolean deleteSysUser(Long userId);
}
