package com.wdg.system.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wdg.common.constant.StatusConstants;
import com.wdg.common.enums.ResultCode;
import com.wdg.common.exception.BusinessException;
import com.wdg.system.dto.LoginDTO;
import com.wdg.system.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class LoginService {

    @Autowired
    private ISysUserService iSysUserService;

    public String login(LoginDTO loginDTO) {
        String userAccount = loginDTO.getUserAccount();
        String password = loginDTO.getPassword();
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserAccount, userAccount);
        SysUser sysUser = iSysUserService.getOne(queryWrapper);
        if (sysUser == null){
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        if (StatusConstants.DEACTIVATE.equals(sysUser.getStatus())){
            throw new BusinessException(ResultCode.USER_ACCOUNT_DISABLED);
        }
        String finalPwd = DigestUtils.md5DigestAsHex((password + sysUser.getSalt()).getBytes(StandardCharsets.UTF_8));
        if (!finalPwd.equals(sysUser.getPassword())){
            throw new BusinessException(ResultCode.USER_PASSWORD_ERROR);
        }
        StpUtil.login(userAccount);
        String tokenValue = StpUtil.getTokenInfo().getTokenValue();
        return tokenValue;
    }
}
