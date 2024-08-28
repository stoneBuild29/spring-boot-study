package com.stone.boot.transaction.service.impl;

import com.stone.boot.transaction.entity.User;
import com.stone.boot.transaction.service.TransactionService;
import com.stone.boot.transaction.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @className: TransactionServiceImpl
 * @author: Scarlet
 * @date: 2024/8/28
 **/
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserService userService;

    /*
    userService.remove(null) 可能是调用了 UserService 的删除方法，其中传递了 null 作为参数。
    这通常意味着删除表中所有的记录，而不是根据特定的条件删除某一部分记录。因此，这个 clean() 方法的作用就是清空数据库中与 User 实体相关的所有数据，通常用来在测试或开发中重置数据表，确保没有残留的测试数据。
     */
    @Override
    public void clean() {
        userService.remove(null);
    }

    //外部方法无事务，两个内部事务各自建立自己的事务
    //外部异常不会影响内部方法的实现，所以两个数据都插入了
    @Override
    public void noTransaction_required_required_externalException() {
        User xiaoShui = new User().setName("小水");
        User xiaoJing = new User().setName("小镜");

        userService.addWithRequired(xiaoShui);
        userService.addWithRequired(xiaoJing);

        throw new RuntimeException();

    }

    /*
    外部方法无异常，内部事务各自建立自己的事务
    内部方法异常，只影响自己数据的插入，不影响其他子方法
    所以小水插入了，小镜没有插入
     */
    @Override
    public void noTransaction_required_requiredException() {
        User xiaoShui = new User().setName("小水");
        User xiaoJing = new User().setName("小镜");

        userService.addWithRequired(xiaoShui);
        userService.addWithRequiredAndException(xiaoJing);

    }

    /*
    外部方法开启事务，所以内部方法均被加入外部方法的事务中。
    第二个方法出现异常，影响到整个事务，所以操作都会回滚。
    结果是两个数据都没有插入。
     */
    @Override
    @Transactional
    public void transaction_required_requiredException() {
        User xiaoShui = new User().setName("小水");
        User xiaoJing = new User().setName("小镜");

        userService.addWithRequired(xiaoShui);
        userService.addWithRequiredAndException(xiaoJing);
    }

    /*
    外部方法开启事务，所以内部方法均被加入外部方法的事务中。
    外部事务发生异常之后，所以内部事务也会回滚。
    结果是两个数据都没有插入。
     */
    @Override
    @Transactional
    public void transaction_required_required_externalException() {
        User xiaoShui = new User().setName("小水");
        User xiaoJing = new User().setName("小镜");

        userService.addWithRequired(xiaoShui);
        userService.addWithRequired(xiaoJing);

        throw new RuntimeException();
    }

    /*
    外部事务和内部方法从属于同一个事务
    虽然异常方法被捕捉了，但是外部事务仍然检测到异常，所以整体回滚。
    小水和小镜都没有插入
     */
    @Override
    @Transactional
    public void transaction_required_requiredException_try() {

        User xiaoShui = new User().setName("小水");
        User xiaoJing = new User().setName("小镜");

        userService.addWithRequired(xiaoShui);
        try{
            userService.addWithRequiredAndException(xiaoJing);
        }catch (Exception e){
            log.error("发生异常，事务回滚");
        }
    }

    /*
    外部事务和第一个事务从属于一个，第二个事务和第三个事务各过各的。
    所以第三个事务异常没有做处理，被外部方法感知。
    小水和水镜未，小镜插入
     */
    @Override
    @Transactional
    public void transaction_required_requiredNew_requiredNewException() {
        User xiaoShui = new User().setName("小水");
        User xiaoJing = new User().setName("小镜");
        User shuiJing = new User().setName("水镜");

        userService.addWithRequired(xiaoShui);
        userService.addWithRequiredNew(xiaoJing);
        userService.addWithRequiredNewAndException(shuiJing);

    }

    /*
        第一个方法和外部方法从属于一个事务，第二个方法的事务相对独立。
        所以抛出异常的时候，小水没有插入，小镜插入了。
     */
    @Override
    @Transactional
    public void transaction_required_requiredNew_externalException() {
        User xiaoShui = new User().setName("小水");
        User xiaoJing = new User().setName("小镜");

        userService.addWithRequired(xiaoShui);
        userService.addWithRequiredNew(xiaoJing);
        throw new RuntimeException();
    }

    /*
        外部方法开启事务，水镜事务的异常被处理了，所以自己回滚。
        其他不受影响，小水、小镜插入，水镜未插入。
     */
    @Override
    @Transactional
    public void transaction_required_requiredNewException_try() {

        User xiaoShui = new User().setName("小水");
        User xiaoJing = new User().setName("小镜");
        User shuiJing = new User().setName("水镜");

        userService.addWithRequired(xiaoShui);
        userService.addWithRequiredNew(xiaoJing);
        try {
            userService.addWithRequiredNewAndException(shuiJing);
        }catch (Exception e){
            log.error("发生异常，事务回滚！");
        }

    }

    /*
          外部方法开启事务，内部方法开启各自的事务。
          小镜插入方法异常，回滚自己的事务。同时没有处理，影响外部事务。
          小水跟随主事务回滚。都没有插入。
     */
    @Override
    @Transactional
    public void transaction_nested_nestedException() {
        User xiaoShui = new User().setName("小水");
        User xiaoJing = new User().setName("小镜");

        userService.addWithNested(xiaoShui);
        userService.addWithNestedAndException(xiaoJing);

    }

    /*
        外部事务回滚，内部嵌套事务也同样回滚。
        小水和小镜都没有插入。
     */
    @Override
    @Transactional
    public void transaction_nested_nested_externalException() {
        User xiaoShui = new User().setName("小水");
        User xiaoJing = new User().setName("小镜");

        userService.addWithNested(xiaoShui);
        userService.addWithNested(xiaoJing);
        throw new RuntimeException();
    }

    /*
        嵌套事务不会影响外部事务，小水和小镜正常插入
     */
    @Override
    @Transactional
    public void transaction_nested_nestedException_try() {
        User xiaoShui = new User().setName("小水");
        User xiaoJing = new User().setName("小镜");
        User shuiJing = new User().setName("水镜");

        userService.addWithRequired(xiaoShui);
        userService.addWithNested(xiaoJing);
        try {
            userService.addWithNestedAndException(shuiJing);
        }catch (Exception e){
            log.error("发生异常，事务回滚！", e);
        }

    }
}
