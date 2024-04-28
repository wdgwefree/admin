package com.wdg.system.service;

import com.wdg.system.dto.SysUserDTO;
import com.wdg.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 */
public interface ISysUserService extends IService<SysUser> {

    void add(SysUserDTO sysUserDTO);

    void deleteById(Long userId);

    void updateSysUser(SysUserDTO sysUserDTO);
}
