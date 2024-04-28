package com.wdg.system.mapper;

import com.wdg.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author wdg
 * @since 2024-04-22
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    void updateSysUser(SysUser sysUser);

}
