package com.stone.boot.mvc;

import lombok.Data;
import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @className: User
 * @author: Scarlet
 * @date: 2024/8/9
 **/
@Data
public class User {

    @NotBlank(message = "名字不能为空")
    private String name;

    @Min(value = 1,message = "年龄要不能小于1")
    @Max(value = 120, message = "年龄要不能大于120")
    private int age;

    @Email(message = "Email格式不正确")
    private String email;

    @Past(message = "生日必须为过去的时间")
    private LocalDate birthday;


}
