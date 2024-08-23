package com.stone.boot.mybatis.dal.entity;

import java.time.LocalDate;
import com.stone.boot.mybatis.common.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author stone
 * @blog https://liushuijinger.blog.csdn.net
 * @since 2024-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 1L;

    private String name;

    private String email;

    private LocalDate birthDay;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
