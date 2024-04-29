package com.wdg.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdg.common.constant.MinioConstants;
import com.wdg.common.constant.StatusConstants;
import com.wdg.common.dto.result.MinioResult;
import com.wdg.common.enums.ResultCode;
import com.wdg.common.exception.BusinessException;
import com.wdg.common.utils.FileService;
import com.wdg.system.dto.SysUserDTO;
import com.wdg.system.entity.SysUser;
import com.wdg.system.mapper.SysUserMapper;
import com.wdg.system.service.ISysUserService;
import com.wdg.system.utils.saltUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private FileService fileService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysUserDTO sysUserDTO) {
        boolean exist = this.checkUserAccountExist(sysUserDTO.getUserAccount());
        if (exist) {
            throw new BusinessException(ResultCode.USER_ACCOUNT_EXIST,sysUserDTO.getUserAccount());
        }
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(sysUserDTO, sysUser);
        sysUser.setStatus(StatusConstants.EXIST);

        //处理头像
        MultipartFile avatarFile = sysUserDTO.getAvatarFile();
        if (avatarFile != null) {
            MinioResult minioResult = fileService.uploadFile(avatarFile, MinioConstants.SYS_USER_IMAGES_PATH);
            sysUser.setAvatar(minioResult.getSaveUrl());
        }

        //目前新增用户时密码采用明文传输
        String salt = saltUtil.getSalt();
        String password = DigestUtils.md5DigestAsHex((sysUserDTO.getPassword()+salt).getBytes(StandardCharsets.UTF_8));
        sysUser.setSalt(salt);
        sysUser.setPassword(password);
        save(sysUser);
        log.info("新增用户:"+sysUser.getUserAccount());
    }

    @Override
    public void deleteById(Long userId) {
        SysUser sysUser = getOne(new LambdaQueryWrapper<SysUser>().select(SysUser::getUserId, SysUser::getStatus));
        if (sysUser == null) {
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        if (StatusConstants.NOT_EXIST.equals(sysUser.getStatus())) {
            throw new BusinessException(ResultCode.USER_ACCOUNT_AlREADY_EXIST);
        }
        sysUser.setStatus(StatusConstants.NOT_EXIST);
        updateById(sysUser);
    }

    @Override
    public void updateSysUser(SysUserDTO sysUserDTO) {
        SysUser oldUser = getById(sysUserDTO.getUserId());
        if (oldUser == null) {
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        //用户账号如果修改了，需要判断用户账号是否已经存在
        if (!oldUser.getUserAccount().equals(sysUserDTO.getUserAccount())) {
            boolean exist = checkUserAccountExist(sysUserDTO.getUserAccount());
            if (exist) {
                throw new BusinessException(ResultCode.USER_ACCOUNT_EXIST);
            }
        }
        SysUser newUser = new SysUser();
        BeanUtil.copyProperties(sysUserDTO, newUser);
        updateById(newUser);
    }


    /**
     * 判断账号是否已经存在
     *
     * @param userAccount 用户账号
     * @return 存在返回turn，不存在返回false
     */
    public boolean checkUserAccountExist(String userAccount) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getUserId)
                .eq(SysUser::getUserAccount, userAccount)
                .ne(SysUser::getStatus, StatusConstants.NOT_EXIST);
        SysUser user = getOne(queryWrapper);
        if (user != null) {
            return true;
        }
        return false;
    }
}
