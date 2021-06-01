package com.healing.tjx.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author tjx
 * @since 2021-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserAuths对象", description = "")
public class UserAuths implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "登陆类型(详见代码中的enum)")
    private String identityType;

    @ApiModelProperty(value = "标识（手机号 邮箱 用户名或第三方应用的唯一标识）")
    private String identifier;

    @ApiModelProperty(value = "密码凭证（站内的保存密码，站外的不保存或保存token）")
    private String credential;

    @ApiModelProperty(value = "初次绑定时间")
    private LocalDateTime bindingTime;

    @ApiModelProperty(value = "最近登陆时间")
    private LocalDateTime loginTime;


}
