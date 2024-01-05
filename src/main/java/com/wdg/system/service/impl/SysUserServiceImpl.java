package com.wdg.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdg.common.constant.StatusConstants;
import com.wdg.common.enums.ResultCode;
import com.wdg.common.exception.BusinessException;
import com.wdg.system.entity.SysUser;
import com.wdg.system.mapper.SysUserMapper;
import com.wdg.system.service.ISysUserService;
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
            throw new BusinessException(ResultCode.USER_ACCOUNT_EXIST);
        }
        //对密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
        sysUser.setPassword(encryptPassword);
        sysUser.setDelFlag("0");
        sysUser.setStatus("0");
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        return save(sysUser);
    }

    @Override
    public boolean updateSysUser(SysUser newUser) {
        SysUser oldUser = getById(newUser.getUserId());
        if (oldUser == null) {
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        if (!oldUser.getUserAccount().equals(newUser.getUserAccount())) {
            LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>()
                    .eq(SysUser::getUserAccount, newUser.getUserAccount())
                    .eq(SysUser::getDelFlag, "0");
            SysUser user = getOne(queryWrapper);
            if (user != null) {
                throw new BusinessException(ResultCode.USER_ACCOUNT_EXIST);
            }
        }
        boolean update = updateById(newUser);
        return update;
    }

    @Override
    public boolean deleteSysUser(Long userId) {
        SysUser user = getById(userId);
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        };
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(SysUser::getDelFlag, StatusConstants.DELETED_USER_TRUE)
                .eq(SysUser::getUserId, userId);
        return update(updateWrapper);
    }
}
