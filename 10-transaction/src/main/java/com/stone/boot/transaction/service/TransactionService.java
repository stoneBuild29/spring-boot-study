package com.stone.boot.transaction.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @className: TransactionService
 * @author: Scarlet
 * @date: 2024/8/28
 **/
public interface TransactionService {

    //开始编写外部方法，创建不同条件
    void clean();

    //不太懂是啥意思呢，外部没有事务，所有内部方法都会创建属于自己的事务
    void noTransaction_required_required_externalException();

    //如果外部有异常的话，不影响内部方法的顺序执行。
    //如果内部有异常的话，内部方法各管各的。
    void noTransaction_required_requiredException();

    @Transactional
    void transaction_required_requiredException();

    @Transactional
    void transaction_required_required_externalException();

    @Transactional
    void transaction_required_requiredException_try();



    @Transactional
    void transaction_required_requiredNew_requiredNewException();

    @Transactional
    void transaction_required_requiredNew_externalException();

    @Transactional
    void transaction_required_requiredNewException_try();



    @Transactional
    void transaction_nested_nestedException();

    @Transactional
    void transaction_nested_nested_externalException();

    @Transactional
    void transaction_nested_nestedException_try();

}
