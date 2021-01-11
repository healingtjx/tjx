package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Author: tjx
 * @Description: 资源分类
 * @Date: 创建于10:20 2021-01-04
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsResourceCategoryChangeParam {

    @ApiModelProperty(value = "修改id(新增的时候不需要传入)")
    private Integer id;

    @ApiModelProperty(value = "排序")
    @NotNull
    private Integer sort;

    @ApiModelProperty(value = "名称")
    @Length(min = 1, max = 20, message = "名称 只能在 1 - 20个字符之间")
    private String name;
}
