package com.wdg.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wdg.common.exception.BusinessException;
import com.wdg.system.entity.SysUser;
import com.wdg.system.mapper.SysUserMapper;
import com.wdg.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public boolean insertSysUser(SysUser sysUser) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserAccount, sysUser.getUserAccount())
                .eq(SysUser::getDelFlag, "0");
        SysUser user = getOne(queryWrapper);
        if (user != null) {
            throw new BusinessException("账号已存在");
        }
        //对密码加密
        String password = sysUser.getPassword();
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(md5DigestAsHex);
        sysUser.setDelFlag("0");
        sysUser.setCreateTime(new Date());
        return save(sysUser);
    }

    @Override
    public boolean updateSysUser(SysUser sysUser) {
        boolean update = updateById(sysUser);
        return update;
    }
}
