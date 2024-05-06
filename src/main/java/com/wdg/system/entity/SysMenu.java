package com.wdg.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author wdg
 * @since 2024-04-22
 */
@Getter
@Setter
@TableName("sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 显示顺序
     */
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 请求地址
     */
    @TableField("url")
    private String url;

    /**
     * 打开方式（M页签 N新窗口）
     */
    @TableField("target")
    private String target;

    /**
     * 菜单类型（M目录 C菜单 F按钮 J接口）
     */
    @TableField("menu_type")
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @TableField("status")
    private String status;

    /**
     * 权限标识
     */
    @TableField("perm")
    private String perm;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
