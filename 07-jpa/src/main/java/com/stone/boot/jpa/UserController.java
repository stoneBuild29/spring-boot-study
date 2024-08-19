package com.stone.boot.jpa;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * @className: UserController
 * @author: Scarlet
 * @date: 2024/8/14
 **/
@Api(tags = "用户接口")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "根据id获得用户信息")
    @GetMapping("/id")
    public User get(@PathVariable int id){
        return userRepository.findById(id).get();
    }

    @ApiOperation(value = "根据名字获得用户信息")
    @GetMapping("/getByName")
    public List<User> getByName(String name){
        return userRepository.findByNameContaining(name);
    }

    @ApiOperation("根据生日获取用户信息")
    @GetMapping("/getByBirthday")
    public List<User> getByBirthday(String birthday){
        return userRepository.fingByBirthDay(LocalDate.parse(birthday));
    }

    @ApiOperation(value = "根据生日获得用户信息（native sql）")
    @GetMapping("/native/mapping")
    public List<User> getByBirthdayNative(String birthday){
        return userRepository.fingByBirthdayNative(birthday);
    }

    @ApiOperation(value = "获取用户列表（分页）")
    @GetMapping
    public Page<User> list(@RequestParam(defaultValue = "id") String property,
                           @RequestParam(defaultValue = "ASC")Sort.Direction direction,
                           @RequestParam(defaultValue = "0") Integer page,
                           @RequestParam(defaultValue = "10")Integer size){

        Pageable pageable = PageRequest.of(page, size, direction, property);

        return userRepository.findAll(pageable);
    }

    @ApiOperation(value = "根据名字查询（分页）")
    @GetMapping("/page/{name}")
    public Page<User> queryByName(@PathVariable String name,
                                  @RequestParam(defaultValue = "id") String property,
                                  @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                  @RequestParam(defaultValue = "0") Integer page,
                                  @RequestParam(defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size, direction, property);
        return userRepository.findByNameContaining(name, pageable);
    }

    @ApiOperation(value = "创建用户")
    @PostMapping
    @Transactional
    public User create(@RequestBody User user){
        return userRepository.save(user);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping ("/{id}")
    public void delete(@PathVariable Integer id){
        userRepository.deleteById(id);
    }

    @ApiOperation(value = "删除所有用户")
    @DeleteMapping
    public int delete(){
        return userRepository.delete();
    }

    @ApiOperation(value = "更新用户")
    @PutMapping
    public User update(@RequestBody User user){
        return userRepository.save(user);
    }
}
