package com.wdg.system.service.impl;

import com.wdg.system.entity.SysRole;
import com.wdg.system.mapper.SysRoleMapper;
import com.wdg.system.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author wdg
 * @since 2024-04-22
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> listRoleByUserId(Long userId) {
        List<SysRole> sysRoles = sysRoleMapper.listRoleByUserId(userId);
        return sysRoles;
    }
}
