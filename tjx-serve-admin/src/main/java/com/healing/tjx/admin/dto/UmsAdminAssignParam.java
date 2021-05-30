package com.healing.tjx.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tjx
 * @Description 管理员分配角色
 * @Date 2021/5/30 10:51 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminAssignParam {

    @ApiModelProperty(value = "管理员编号", required = true)
    private long adminId;

    @ApiModelProperty(value = "角色编号", required = true)
    private Long[] roleIds;
}
