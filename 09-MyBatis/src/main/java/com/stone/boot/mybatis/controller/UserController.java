package com.stone.boot.mybatis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.boot.mybatis.dal.vo.PageReqVO;
import com.stone.boot.mybatis.mapper.UserMapper;
import com.stone.boot.mybatis.service.UserService;
import com.stone.boot.mybatis.dal.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author stone
 * @blog https://liushuijinger.blog.csdn.net
 * @since 2024-08-21
 */
@RestController
@RequestMapping("/user")
@Api(value = "User对象",tags = "")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "查询")
    @GetMapping(value = "/{id}")
    public User get(@PathVariable Integer id) {
      return userService.getById(id);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/list")
    public Page<User> list(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer size){
        return userService.page(new Page<>(pageNum, size));
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

    @ApiOperation(value = "按照条件查找")
    @GetMapping("/find")
    public List<User> findUser(@RequestBody PageReqVO pageReqVO){
        return userService.findUser(pageReqVO);
    }

}
