package com.wdg.system.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wdg.common.config.TokenProperties;
import com.wdg.common.constant.RedisConstants;
import com.wdg.common.constant.ResultCode;
import com.wdg.common.constant.StatusConstants;
import com.wdg.common.exception.BusinessException;
import com.wdg.common.util.RedisCache;
import com.wdg.system.dto.LoginDTO;
import com.wdg.system.entity.SysMenu;
import com.wdg.system.entity.SysRole;
import com.wdg.system.entity.SysUser;
import com.wdg.system.util.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
    private ISysMenuService iSysMenuService;

    @Autowired
    private ISysRoleService iSysRoleService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TokenProperties tokenProperties;

    public String login(LoginDTO loginDTO) {

        String userAccount = loginDTO.getUserAccount();
        String password = loginDTO.getPassword();

        //账号、状态、密码校验
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserAccount, userAccount);
        SysUser sysUser = iSysUserService.getOne(queryWrapper);
        if (sysUser == null) {
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_FOUND);
        }
        if (StatusConstants.DEACTIVATE.equals(sysUser.getStatus())) {
            throw new BusinessException(ResultCode.USER_ACCOUNT_DISABLED);
        }
        String pwd = DigestUtils.md5DigestAsHex((RsaUtil.decryptByPrivateKey(password) + sysUser.getSalt()).getBytes(StandardCharsets.UTF_8));
        if (!pwd.equals(sysUser.getPassword())) {
            throw new BusinessException(ResultCode.USER_PASSWORD_ERROR);
        }

        Long userId = sysUser.getUserId();

        //用户角色、权限查询
        List<String> permissions = listPermissionByUserId(userId);
        List<String> roles = listRoleByUserId(userId);

        //创建token
        String tokenKey = userId + ":" + IdUtil.getSnowflakeNextIdStr();
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", userId);
        map.put("userAccount", userAccount);
        map.put("tokenKey", tokenKey);
        String token = JWTUtil.createToken(map, tokenProperties.getSecret().getBytes());

        //缓存token与session
        redisCache.setCacheObject(RedisConstants.LOGIN_TOKEN+tokenKey, token, tokenProperties.getExpireTime(), TimeUnit.MINUTES);
        redisCache.setCacheObject(RedisConstants.LOGIN_SESSION+userId, sysUser, tokenProperties.getExpireTime(), TimeUnit.MINUTES);
        return token;
    }


    /**
     * 获取用户权限标识list
     *
     * @param userId
     * @return
     */
    public List<String> listPermissionByUserId(Long userId) {
        List<SysMenu> sysMenus = iSysMenuService.listPermissionByUserId(userId);
        return sysMenus.stream().map(SysMenu::getPerm).collect(Collectors.toList());
    }

    /**
     * 获取用户角色标识list
     *
     * @param userId
     * @return
     */
    public List<String> listRoleByUserId(Long userId) {
        List<SysRole> sysRoles = iSysRoleService.listRoleByUserId(userId);
        return sysRoles.stream().map(SysRole::getRoleKey).collect(Collectors.toList());
    }
}
