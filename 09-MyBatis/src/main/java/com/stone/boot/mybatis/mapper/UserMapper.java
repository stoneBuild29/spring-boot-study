package com.stone.boot.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.stone.boot.mybatis.dal.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.boot.mybatis.dal.vo.PageReqVO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stone
 * @blog https://liushuijinger.blog.csdn.net
 * @since 2024-08-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    default List<User> findUser(PageReqVO pageReqVO){
        return selectList(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, pageReqVO.getEmail())
                .like(User::getName, pageReqVO.getName())
                .between(User::getBirthDay, LocalDate.parse("2003-12-31"), LocalDate.parse("2023-12-31")));
    }

}
