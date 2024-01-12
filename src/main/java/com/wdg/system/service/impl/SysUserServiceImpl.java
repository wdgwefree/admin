package com.wdg.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdg.common.constant.StatusConstants;
import com.wdg.common.enums.ResultCode;
import com.wdg.common.exception.BusinessException;
import com.wdg.system.dto.SysUserDTO;
import com.wdg.system.entity.SysUser;
import com.wdg.system.mapper.SysUserMapper;
import com.wdg.system.service.ISysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public boolean insertSysUser(SysUserDTO sysUserDTO) {

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserAccount, sysUserDTO.getUserAccount())
                .eq(SysUser::getDelFlag, "0");
        SysUser user = getOne(queryWrapper);
        if (user != null) {
            throw new BusinessException(ResultCode.USER_ACCOUNT_EXIST);
        }
        //对密码加密
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(sysUserDTO, sysUser);
        return save(sysUser);
    }

    @Override
    public boolean updateSysUser(SysUserDTO sysUserDTO) {
        SysUser oldUser = getById(sysUserDTO.getUserId());
        if (oldUser == null) {
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        //用户账号有变化，需要判断用户账号是否已经存在
        if (!oldUser.getUserAccount().equals(sysUserDTO.getUserAccount())) {
            LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>()
                    .eq(SysUser::getUserAccount, sysUserDTO.getUserAccount())
                    .eq(SysUser::getDelFlag, "0");
            SysUser user = getOne(queryWrapper);
            if (user != null) {
                throw new BusinessException(ResultCode.USER_ACCOUNT_EXIST);
            }
        }
        SysUser newUser = new SysUser();
        BeanUtil.copyProperties(sysUserDTO, newUser);
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
