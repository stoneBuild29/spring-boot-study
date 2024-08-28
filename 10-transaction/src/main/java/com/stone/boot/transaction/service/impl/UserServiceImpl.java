package com.stone.boot.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stone.boot.transaction.entity.User;
import com.stone.boot.transaction.mapper.UserMapper;
import com.stone.boot.transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @className: UserServiceImpl
 * @author: Scarlet
 * @date: 2024/8/28
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addWithRequired(User user) {
        userMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addWithRequiredAndException(User user) {
        userMapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addWithRequiredNew(User user) {
        userMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addWithRequiredNewAndException(User user) {
        userMapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addWithNested(User user) {
        userMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addWithNestedAndException(User user) {
        userMapper.insert(user);
        throw new RuntimeException();
    }
}
