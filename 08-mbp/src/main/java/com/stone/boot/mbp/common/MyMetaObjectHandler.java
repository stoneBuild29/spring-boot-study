package com.stone.boot.mbp.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.google.errorprone.annotations.CompileTimeConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @className: MyMetaObjectHandler
 * @author: Scarlet
 * @date: 2024/8/20
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject,"updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "creator", this::getCurrentUser, String.class);
        this.strictInsertFill(metaObject,"modifier", this::getCurrentUser, String.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject,"modifier", this::getCurrentUser, String.class);

    }

    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal){
        Object obj = fieldVal.get();

        if (Objects.nonNull(obj)){
            metaObject.setValue(fieldName, obj);
        }

        return this;
    }

    private String getCurrentUser(){
        return "管理员" + (int) (Math.random() * 10);
    }

}
