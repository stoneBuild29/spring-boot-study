package com.stone.boot.transaction.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.boot.transaction.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author stone
 * @blog https://liushuijinger.blog.csdn.net
 * @since 2024-08-23
 */
@RestController
@RequestMapping("/user")
@Api(value = "User对象",tags = "")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "查询")
    @GetMapping(value = "/{id}")
    public User get(@PathVariable Integer id) {
      return userService.getById(id);
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public Boolean add(@RequestBody User user) {
      return userService.save(user);
    }

    @ApiOperation(value = "修改")
    @PutMapping
    public Boolean modify(@RequestBody User user) {
      return userService.updateById(user);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/{id}")
    public Boolean remove(@PathVariable Integer id) {
      return userService.removeById(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    public Page<User> list(@RequestParam(defaultValue = "1") Integer current,
                           @RequestParam(defaultValue = "10") Integer size){
        return userService.page(new Page<>(current,size));
    }


}
