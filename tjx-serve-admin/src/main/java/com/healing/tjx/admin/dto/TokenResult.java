package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @作者: tjx
 * @描述: 登陆成功后返回结果
 * @创建时间: 创建于13:45 2020-12-14
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class TokenResult {

    @ApiModelProperty(value = "用户认证标识")
    private String token;

    @ApiModelProperty(value = "标识key")
    private String tokenHeader;

}
