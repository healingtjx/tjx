package com.healing.tjx.admin.dto;

import com.healing.tjx.common.api.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @作者: tjx
 * @描述: 资源分页查询
 * @创建时间: 创建于14:16 2020-12-18
 **/
@Getter
@Setter
public class UmsResourcePageParam extends PageParam {

    @ApiModelProperty(value = "名称")
    @Length(min = 1, max = 20, message = "名称 只能在 1 - 20个字符之间")
    private String name;

    @ApiModelProperty(value = "资源URL")
    @Length(min = 1, max = 50, message = "资源URL 只能在 1 - 50个字符之间")
    private String url;

    @ApiModelProperty(value = "资源分类id")
    private Integer categoryId;

}
