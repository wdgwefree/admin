package com.wdg.system.util;

import com.wdg.common.config.TokenProperties;
import com.wdg.common.constant.RedisConstants;
import com.wdg.common.util.RedisCache;
import com.wdg.system.dto.LoginSessionDTO;
import com.wdg.system.dto.LoginTokenDTO;
import com.wdg.system.entity.SysMenu;
import com.wdg.system.entity.SysRole;
import com.wdg.system.entity.SysUser;
import com.wdg.system.service.ISysMenuService;
import com.wdg.system.service.ISysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 异步工具
 *
 * @author: wdg
 * @date: 2024/5/30
 **/
@Component
public class AsyncUtil {

    @Autowired
    private ISysMenuService iSysMenuService;

    @Autowired
    private ISysRoleService iSysRoleService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TokenProperties tokenProperties;

    /**
     * 异步处理会话和token
     *
     * @param sysUser
     * @param loginTokenDTO
     */
    @Async
    public void asyncSession(SysUser sysUser, LoginTokenDTO loginTokenDTO) {
        Long userId = sysUser.getUserId();
        //用户角色、权限查询
        List<String> permissions = listPermissionByUserId(userId);
        List<String> roles = listRoleByUserId(userId);

        synchronized (userId.toString().intern()) {
            //redis是否已有会话记录
            LoginSessionDTO loginSessionDTO = redisCache.getCacheObject(RedisConstants.LOGIN_SESSION + userId);
            if (loginSessionDTO == null) {
                loginSessionDTO = new LoginSessionDTO();
                BeanUtils.copyProperties(sysUser, loginSessionDTO);
                loginSessionDTO.setPermissions(permissions);
                loginSessionDTO.setRoles(roles);
                LinkedList<LoginTokenDTO> loginTokenDTOS = new LinkedList<>();
                loginSessionDTO.setLoginTokenDTOS(loginTokenDTOS);
            }

            //是否允许多端登录
            Boolean concurrent = tokenProperties.getConcurrent();
            if (concurrent) {
                if (loginSessionDTO.getLoginTokenDTOS().size() >= tokenProperties.getConcurrentMax()) {
                    //超出最大允许登录数,删除会话中最早登录的token
                    Iterator<LoginTokenDTO> iterator = loginSessionDTO.getLoginTokenDTOS().iterator();
                    LoginTokenDTO next = iterator.next();
                    redisCache.deleteObject(RedisConstants.LOGIN_TOKEN + next.getTokenKey());
                    iterator.remove();
                }
                loginSessionDTO.getLoginTokenDTOS().add(loginTokenDTO);

            } else {
                //禁止多端登录,始终保持只有一个token生效
                LinkedList<LoginTokenDTO> loginTokenDTOS = new LinkedList<>();
                loginTokenDTOS.add(loginTokenDTO);
                loginSessionDTO.setLoginTokenDTOS(loginTokenDTOS);

            }
            //缓存用户会话
            redisCache.setCacheObject(RedisConstants.LOGIN_SESSION + userId, loginSessionDTO, tokenProperties.getExpireTime(), TimeUnit.MINUTES);
        }

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
