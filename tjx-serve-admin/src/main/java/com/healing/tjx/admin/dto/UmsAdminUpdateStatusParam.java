package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @作者: tjx
 * @描述: 修改管理员状态
 * @创建时间: 创建于11:20 2020-12-29
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminUpdateStatusParam {

    @NotNull
    @ApiModelProperty(value = "修改id", required = true)
    private Integer id;

    @NotNull
    @Range(min = 0, max = 1)
    @ApiModelProperty(value = "状态 0 禁用1 开启", required = true)
    private Integer status;


}
