package com.stone.boot.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

/**
 * @className: UserRepository
 * @author: Scarlet
 * @date: 2024/8/14
 **/

public interface UserRepository extends JpaRepository<User, Integer> {

    //两个普通的方法
    List<User> findByNameContaining(String name);

    Page<User> findByNameContaining(String name, Pageable pageable);

    //写JPQL作为query的两个方法
    @Query("select u from User u where u.birthday = ?1")
    List<User> fingByBirthDay(LocalDate birthday);

    //SQL
    @Query(value = "select * from user where birthday = birthday", nativeQuery = true)
    List<User> fingByBirthdayNative(@Param("birthday") String birthday);

    @Modifying
    @Transactional
    @Query(value = "delete from User")
    int delete();
}
