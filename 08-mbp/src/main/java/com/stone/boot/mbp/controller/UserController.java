package com.stone.boot.mbp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.boot.mbp.entity.User;
import com.stone.boot.mbp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className: UserController
 * @author: Scarlet
 * @date: 2024/8/20
 **/
@RequestMapping("/user")
@RestController
@Api(value = "User对象",tags = "用户信息")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查找")
    public User get(@PathVariable Integer id){
        return userService.getById(id);
    }

    @GetMapping("/list")
    @ApiOperation("分页查询")
    public Page<User> list(@RequestParam(defaultValue = "1") Integer current,
                           @RequestParam(defaultValue = "10") Integer size){
        return userService.page(new Page<>(current,size));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public Boolean add(@RequestBody User user){
        return userService.save(user);
    }

    @PostMapping(value = "/delete/{id}/")
    @ApiOperation(value = "删除")
    public Boolean remove(@PathVariable Integer id){
        return userService.removeById(id);
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改")
    public Boolean modify(@RequestBody User user){
        return userService.updateById(user);
    }

}
