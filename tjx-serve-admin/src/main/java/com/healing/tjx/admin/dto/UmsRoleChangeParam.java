package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * @作者: tjx
 * @描述: 新增/修改 角色
 * @创建时间: 创建于13:45 2020-12-30
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsRoleChangeParam {

    @ApiModelProperty(value = "修改id(新增的时候不需要传入)")
    private Integer id;

    @ApiModelProperty(value = "名称")
    @Length(min = 3, max = 20, message = "名称 只能在 3 - 20个字符之间")
    private String name;

    @ApiModelProperty(value = "描述")
    @Length(max = 500, message = "描述必须小于500个字符")
    private String description;


}
