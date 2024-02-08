package com.wdg.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wdg.common.utils.ValidatedGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 系统用户
 */
@Data
public class SysUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
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
    @NotBlank(message = "userAccount不能为空", groups = {ValidatedGroup.Insert.class})
    @Length(min = 3, max = 20, message = "用户账号长度在3-20之间")
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户类型（00系统用户 01注册用户）
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

    /**
     * 头像文件
     */
    private MultipartFile avatarFile;

    /**
     * 密码
     */
    @NotBlank(message = "password不能为空", groups = {ValidatedGroup.Insert.class})
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String delFlag;

    /**
     * 备注
     */
    private String remark;
}
