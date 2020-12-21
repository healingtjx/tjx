package com.healing.tjx.admin.dto;

import com.healing.tjx.admin.entity.UmsMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @作者: tjx
 * @描述: 用户信息返回类
 * @创建时间: 创建于14:16 2020-12-18
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminInfoResult {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "菜单")
    private List<UmsMenu> menus;


    @ApiModelProperty(value = "角色")
    private List<String> roles;


}
