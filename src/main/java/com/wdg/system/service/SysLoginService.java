package com.wdg.system.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wdg.common.enums.ResultCode;
import com.wdg.common.exception.BusinessException;
import com.wdg.system.dto.LoginBody;
import com.wdg.system.entity.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Component
public class SysLoginService {

    @Resource
    private ISysUserService iSysUserService;

    @Resource
    private TokenService tokenService;

    public String login(LoginBody loginBody) {

        String userAccount = loginBody.getUserAccount();
        String password = loginBody.getPassword();
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserAccount, userAccount)
                .eq(SysUser::getDelFlag, "0");
        SysUser sysUser = iSysUserService.getOne(queryWrapper);
        if (sysUser == null) {
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        String realPwd = sysUser.getPassword();
        String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!md5Pwd.equals(realPwd)) {
            throw new BusinessException(ResultCode.USER_PASSWORD_ERROR);
        }
        if (sysUser.getStatus().equals("1")) {
            throw new BusinessException(ResultCode.USER_ACCOUNT_DISABLED);
        }
        String token = tokenService.generateToken(sysUser);
        return token;
    }


}
