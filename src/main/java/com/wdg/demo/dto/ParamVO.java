package com.wdg.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description:
 * @author: wdg
 * @create: 2023-11-22 14:41
 */
@Data
@ApiModel(value = "参数DTO")
public class ParamVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "userCode不能为空")
    @ApiModelProperty(value = "用户code", required = true)
    private String userCode;

    @NotNull(message = "userName不能为null")
    @ApiModelProperty(value = "用户姓名", required = true)
    private String userName;

    @Max(value = 200,message = "您确定年龄200多岁？？？")
    @ApiModelProperty(value = "用户年龄", required = false)
    private Integer age;

}
