package com.stone.boot.jpa;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @className: BaseEnity
 * @author: Scarlet
 * @date: 2024/8/14
 **/
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEnity {

    @CreatedBy
    @Column(updatable = false, length = 20)
    private String creator;

    @LastModifiedBy
    @Column(length = 20)
    private String modifier;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;
}
