package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: tjx
 * @Description: 授权返回结果
 * @Date: 创建于11:29 2021-01-05
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class AllocResult {

    @ApiModelProperty(value = "菜单列表")
    List<TreeResult> tree;

    @ApiModelProperty(value = "授权id集合")
    List<Long> ids;
}
