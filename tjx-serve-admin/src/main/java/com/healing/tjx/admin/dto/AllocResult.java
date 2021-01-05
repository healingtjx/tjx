package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @作者: tjx
 * @描述: 授权返回结果
 * @创建时间: 创建于11:29 2021-01-05
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class AllocResult {

    @ApiModelProperty(value = "菜单列表")
    List<TreeResult> tree;

    @ApiModelProperty(value = "授权id集合")
    List<Long> ids;
}
