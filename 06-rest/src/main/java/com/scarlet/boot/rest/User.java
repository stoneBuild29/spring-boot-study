package com.scarlet.boot.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @className: User
 * @author: Scarlet
 * @date: 2024/8/14
 **/

@Data
@ApiModel("用户信息")
public class User {

    @ApiModelProperty("用户id")
    private int id;

    @ApiModelProperty("名字")
    @NotBlank(message = "名字不能为空")
    private String name;

    @ApiModelProperty("年龄")
    @Min(value = 1, message = "年龄要不能小于1")
    @Max(value = 120, message = "年龄要不能大于120")
    private int age;

    @ApiModelProperty("邮箱")
    @Email(message = "Email格式不正确")
    private String email;

    @ApiModelProperty("生日")
    @Past(message = "生日必须为过去的时间")
    private LocalDate birthday;


}
