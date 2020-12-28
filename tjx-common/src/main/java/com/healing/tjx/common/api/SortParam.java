package com.healing.tjx.common.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @作者: tjx
 * @描述: 用于数据排序
 * @创建时间: 创建于16:23 2020-12-28
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SortParam {

    @ApiModelProperty(value = "排序字段")
    @NotNull
    private String key;

    @ApiModelProperty(value = "是否降序(false 升序|true 降序)")
    @NotNull
    private Boolean isDescen;
}
