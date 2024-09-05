package com.stone.boot.mvc;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @className: ParamController
 * @author: Scarlet
 * @date: 2024/8/9
 **/
@RestController
@Validated
public class ParamController {

    @GetMapping("/noAnnotation")
    public User noAnnotation(User user){
        return user;
    }

    @GetMapping("/requestParam")
    public User requestParam(@RequestParam String name, @RequestParam int age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @GetMapping("/pathVariable/{name}/{age}")
    public User pathVariable(@PathVariable String name, @PathVariable int age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("/requestBody")
    public User requestBody(@RequestBody @Valid User user){
        return user;
    }

    @PostMapping("/post")
    public String post(String name){
        return name;
    }

}
