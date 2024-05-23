package com.wdg.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wdg.common.constant.ResultCode;
import com.wdg.common.constant.StatusConstants;
import com.wdg.common.exception.BusinessException;
import com.wdg.system.dto.LoginDTO;
import com.wdg.system.entity.SysUser;
import com.wdg.system.util.RsaUtil;
import com.wdg.system.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 登录服务类
 *
 * @author: wdg
 * @date: 2024/5/20
 **/
@Service
public class LoginService {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private TokenUtil tokenUtil;

    public String login(LoginDTO loginDTO) {

        String userAccount = loginDTO.getUserAccount();
        String password = loginDTO.getPassword();

        //账号、状态、密码校验
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserAccount, userAccount).ne(SysUser::getStatus, StatusConstants.NOT_EXIST);
        SysUser sysUser = iSysUserService.getOne(queryWrapper);
        if (sysUser == null) {
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_FOUND);
        }
        if (StatusConstants.DEACTIVATE.equals(sysUser.getStatus())) {
            throw new BusinessException(ResultCode.USER_ACCOUNT_DISABLED);
        }
        tokenUtil.checkLoginLock(sysUser.getUserId());
        String pwd = null;
        try {
            pwd = DigestUtils.md5DigestAsHex((RsaUtil.decryptByPrivateKey(password) + sysUser.getSalt()).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            tokenUtil.loginPasswordError(sysUser.getUserId());
            throw new BusinessException(ResultCode.USER_PASSWORD_ERROR);
        }
        if (!pwd.equals(sysUser.getPassword())) {
            tokenUtil.loginPasswordError(sysUser.getUserId());
            throw new BusinessException(ResultCode.USER_PASSWORD_ERROR);
        }

        String token = tokenUtil.generateToken(sysUser);
        return token;
    }
}
