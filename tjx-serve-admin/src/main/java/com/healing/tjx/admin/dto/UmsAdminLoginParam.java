package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @作者: tjx
 * @描述: 后台用户登陆参数
 * @创建时间: 创建于10:43 2020-12-14
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminLoginParam {

    @NotEmpty
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotEmpty
    @ApiModelProperty(value = "密码(md5(password))", required = true)
    private String password;

}
