package com.wdg.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdg.common.constant.StatusConstants;
import com.wdg.common.enums.ResultCode;
import com.wdg.common.exception.BusinessException;
import com.wdg.system.dto.SysUserDTO;
import com.wdg.system.entity.SysUser;
import com.wdg.system.mapper.SysUserMapper;
import com.wdg.system.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author wdg
 * @since 2024-04-22
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public void add(SysUserDTO sysUserDTO) {
        boolean exist = this.checkUserAccountExist(sysUserDTO.getUserAccount());
        if (exist){
            throw new BusinessException(ResultCode.USER_ACCOUNT_EXIST);
        }
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(sysUserDTO, sysUser);
        sysUser.setStatus(StatusConstants.EXIST);


        save(sysUser);
    }


    /**
     * 判断账号是否已经存在
     *
     * @param userAccount 用户账号
     * @return 存在返回turn，不存在返回false
     */
    public boolean checkUserAccountExist(String userAccount) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserAccount, userAccount)
                .ne(SysUser::getStatus, StatusConstants.NOT_EXIST);
        SysUser user = getOne(queryWrapper);
        if (user != null) {
            return true;
        }
        return false;
    }
}
