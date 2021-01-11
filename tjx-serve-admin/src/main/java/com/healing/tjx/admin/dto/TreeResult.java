package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: tjx
 * @Description: 用于返回树形结构数据
 * @Date: 创建于11:18 2021-01-05
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class TreeResult {


    @ApiModelProperty(value = "菜单id")
    private Integer id;

    @ApiModelProperty(value = "用户认证标识")
    private String label;

    @ApiModelProperty(value = "用户认证标识")
    private List<TreeResult> children;

}
