package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @Author: tjx
 * @Description: 修改状态
 * @Date: 创建于11:20 2020-12-29
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateStatusParam {

    @NotNull
    @ApiModelProperty(value = "修改id", required = true)
    private Integer id;

    @NotNull
    @Range(min = 0, max = 1)
    @ApiModelProperty(value = "状态 0 禁用 1 开启", required = true)
    private Integer status;


}
