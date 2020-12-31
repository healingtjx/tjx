package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @作者: tjx
 * @描述: 新增/ 修改 菜单
 * @创建时间: 创建于10:35 2020-12-31
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsMenuChangeParam {

    @ApiModelProperty(value = "修改id(新增的时候不需要传入)")
    private Integer id;

    @ApiModelProperty(value = "名称")
    @Length(min = 2, max = 6, message = "名称 只能在 2 - 6个字符之间")
    private String name;

    @ApiModelProperty(value = "前端标题")
    @Length(min = 1, max = 20, message = "前端标题 只能在 1 - 20个字符之间")
    private String title;

    @ApiModelProperty(value = "前端图标")
    @NotEmpty
    private String icon;

    @ApiModelProperty(value = "排序")
    @NotNull
    private Integer sort;

    @ApiModelProperty(value = "父类id")
    @Range(min = 0, message = "父类id必须要大于0")
    private Integer parentId;

}
