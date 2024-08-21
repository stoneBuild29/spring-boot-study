package com.stone.boot.mbp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stone.boot.mbp.entity.User;
import com.stone.boot.mbp.mapper.UserMapper;
import com.stone.boot.mbp.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @className: UserServiceImpl
 * @author: Scarlet
 * @date: 2024/8/20
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
