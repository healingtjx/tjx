package com.healing.tjx.common.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: tjx
 * @Description: 用于数据排序
 * @Date: 创建于16:23 2020-12-28
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SortParam {

    @ApiModelProperty(value = "排序字段")
    private String sortKey;

    @ApiModelProperty(value = "是否降序(false 升序|true 降序)")
    private Boolean sortDescen;
}
