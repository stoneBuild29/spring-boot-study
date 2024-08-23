package com.stone.boot.mybatis.dal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * @className: PageReqVO
 * @author: Scarlet
 * @date: 2024/8/21
 **/
@Data
@ApiModel(value="查询VO", description="")
public class PageReqVO {

    private String name;

    private String email;

    private LocalDate birthDay;
}
