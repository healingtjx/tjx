package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @Author: tjx
 * @Description: 赋予权限请求
 * @Date: 创建于15:24 2021-01-05
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsRoleAllocParam {

    @NotNull
    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @NotNull
    @ApiModelProperty(value = "关系ids")
    private Integer [] relationIds;


}
