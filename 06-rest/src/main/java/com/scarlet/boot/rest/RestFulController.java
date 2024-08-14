package com.scarlet.boot.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @className: RestController
 * @author: Scarlet
 * @date: 2024/8/14
 **/
@RestController
@Api(tags = "RESTful接口")
@RequestMapping("/rest")
public class RestFulController {

    @GetMapping("/swagger")
    @ApiOperation(value = "Swagger窗口")
    public String swagger(){
        return "Swagger Method";
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "根据id获取用户信息")
    public User get(@PathVariable @ApiParam("用户ID") int id){

        //演示示例，是集开发与数据库交互
        User user = new User();
        user.setId(id);
        user.setName("ID为" + id + "的用户");
        user.setAge(18);
        user.setEmail("scarlet@163.com");
        return user;
    }


    @ApiOperation(value = "创建用户")
    @PostMapping("/user")
    public boolean create(User user){
        if(user != null){
            return true;
        }
        return false;
    }

    @ApiOperation(value = "更新用户")
    @PostMapping("/update")
    public boolean update(@RequestBody User user){
        if(user != null){
            return true;
        }
        return false;
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/user/{id}")
    public boolean delete(@PathVariable int id){
        if(id > 0){
            return true;
        }
        return false;
    }



}
