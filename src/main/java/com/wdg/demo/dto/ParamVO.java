package com.wdg.demo.dto;

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
public class ParamVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "userCode不能为空")
    private String userCode;

    @NotNull(message = "userName不能为null")
    private String userName;

    @Max(value = 200,message = "您确定年龄200多岁？？？")
    private Integer age;

}
