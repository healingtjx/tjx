package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @作者: tjx
 * @描述: 新增/修改 角色
 * @创建时间: 创建于13:45 2020-12-30
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsResourceChangeParam {

    @ApiModelProperty(value = "修改id(新增的时候不需要传入)")
    private Integer id;

    @NotNull
    @ApiModelProperty(value = "资源分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "资源URL")
    @Length(min = 1, max = 50, message = "资源URL 只能在 1 - 50个字符之间")
    private String url;

    @ApiModelProperty(value = "名称")
    @Length(min = 1, max = 20, message = "名称 只能在 1 - 20个字符之间")
    private String name;

    @ApiModelProperty(value = "描述")
    @Length(max = 500, message = "描述必须小于500个字符")
    private String description;


}
