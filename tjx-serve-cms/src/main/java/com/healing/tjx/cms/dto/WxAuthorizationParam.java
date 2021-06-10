package com.healing.tjx.cms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author tjx
 * @Description 微信小程序登陆参数
 * @Date 2021/6/10 9:25 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxAuthorizationParam {

    @NotNull
    @ApiModelProperty(value = "wx.login获取的code")
    private String code;

    @NotNull
    @ApiModelProperty(value = "iv")
    private String iv;

    @NotNull
    @ApiModelProperty(value = "encryptedData")
    private String encryptedData;

    @NotNull
    @ApiModelProperty(value = "名称")
    private String nickName;

    @NotNull
    @ApiModelProperty(value = "头像")
    private String avatarUrl;

    @NotNull
    @ApiModelProperty(value = "性别")
    private String gender;
}
