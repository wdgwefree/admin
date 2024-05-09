package com.wdg.system.dto;


import com.wdg.common.util.ValidatedGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 菜单权限 DTO
 */
public class MenuDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @NotNull(message = "menuId不能为空", groups = {ValidatedGroup.Update.class})
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 打开方式（M页签 N新窗口）
     */
    private String target;

    /**
     * 菜单类型（M目录 C菜单 F按钮  J接口）
     */
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String status;

    /**
     * 权限标识
     */
    private String perm;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

}
