package com.healing.tjx.cms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: tjx
 * @Description: 登陆成功后返回结果
 * @Date: 创建于13:45 2020-12-14
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class TokenResult {

    @ApiModelProperty(value = "用户认证标识")
    private String token;

    @ApiModelProperty(value = "标识key")
    private String tokenHeader;

}
