package com.stone.boot.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.boot.transaction.entity.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @className: UserService
 * @author: Scarlet
 * @date: 2024/8/28
 **/

public interface UserService extends IService<User> {

    //编写内部方法，设置为不同的事务传播方式

    /*
    设置为REQUIRED的事务传播方式
     */
    @Transactional(propagation = Propagation.REQUIRED)
    void addWithRequired(User user);

    /*
    设置为REQUIRED的事务传播方式，同时测试处理异常的方式
     */
    @Transactional(propagation = Propagation.REQUIRED)
    void addWithRequiredAndException(User user);

    /*
    设置为REQUIRES_NEW的事务传播方式
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void addWithRequiredNew(User user);

    /*
    设置为REQUIRES_NEW的事务传播方式，同时测试处理异常的方式
    新建的事务独立于外部事务，所以异常处理之后不会对其他事务产生影响。
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void addWithRequiredNewAndException(User user);

    /*
    设置为NESTED的事务传播方式，内部方法会嵌套在外部事务中
     */
    @Transactional(propagation = Propagation.NESTED)
    void addWithNested(User user);

    /*
    设置为NESTED的事务传播方式，内部方式发生异常会不会影响其他方法？
     */
    @Transactional(propagation = Propagation.NESTED)
    void addWithNestedAndException(User user);

}
