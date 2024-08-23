package com.stone.boot.mybatis.service.impl;

import com.stone.boot.mybatis.dal.entity.User;
import com.stone.boot.mybatis.dal.vo.PageReqVO;
import com.stone.boot.mybatis.mapper.UserMapper;
import com.stone.boot.mybatis.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stone
 * @blog https://liushuijinger.blog.csdn.net
 * @since 2024-08-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findUser(PageReqVO pageReqVO) {
        return userMapper.findUser(pageReqVO);
    }
}
