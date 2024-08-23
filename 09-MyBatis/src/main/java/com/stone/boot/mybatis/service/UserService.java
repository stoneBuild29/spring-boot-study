package com.stone.boot.mybatis.service;

import com.stone.boot.mybatis.dal.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.boot.mybatis.dal.vo.PageReqVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stone
 * @blog https://liushuijinger.blog.csdn.net
 * @since 2024-08-21
 */
public interface UserService extends IService<User> {

    List<User> findUser(PageReqVO pageReqVO);

}
