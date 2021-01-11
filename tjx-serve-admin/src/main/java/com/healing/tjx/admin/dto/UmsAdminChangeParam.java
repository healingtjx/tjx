package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;


/**
 * @Author: tjx
 * @Description: 新增/修改 管理员
 * @Date: 创建于14:37 2020-12-29
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminChangeParam {

    @ApiModelProperty(value = "修改id(新增的时候不需要传入)")
    private Integer id;

    @ApiModelProperty(value = "密码: 创建的时候必须有，修改的时候可以为空")
    private String password;

    @ApiModelProperty(value = "账号", required = true)
    @Length(min = 3, max = 20, message = "账号 只能在 3 - 20个字符之间")
    private String username;

    @ApiModelProperty(value = "昵称", required = true)
    @Length(min = 3, max = 20, message = "昵称 只能在 3 - 20个字符之间")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "备注")
    private String note;


}
