package com.wdg.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wdg.common.utils.ValidatedGroup;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 */
@Getter
@Setter
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @NotBlank(message = "userId不能为空", groups = {ValidatedGroup.Update.class})
    private Long userId;

    /**
     * 部门ID
     */
    @TableField("dept_id")
    private Long deptId;

    /**
     * 用户账号
     */
    @TableField("user_account")
    @NotBlank(message = "userAccount不能为空", groups = {ValidatedGroup.Insert.class})
    @Length(min = 6, max = 20, message = "用户账号长度在3-20之间")
    private String userAccount;

    /**
     * 用户昵称
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户类型（00系统用户 01注册用户）
     */
    @TableField("user_type")
    private String userType;

    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @TableField("sex")
    private String sex;

    /**
     * 头像路径
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 密码
     */
    @TableField("password")
    @NotBlank(message = "password不能为空", groups = {ValidatedGroup.Insert.class})
    @Length(min = 6, max = 32, message = "密码长度在6-20之间")
    private String password;

    /**
     * 盐加密
     */
    @TableField("salt")
    private String salt;

    /**
     * 帐号状态（0正常 1停用）
     */
    @TableField("status")
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField("del_flag")
    private String delFlag;

    /**
     * 最后登录IP
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @TableField("login_date")
    private Date loginDate;

    /**
     * 密码最后更新时间
     */
    @TableField("pwd_update_date")
    private Date pwdUpdateDate;

    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", update = "now()")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
