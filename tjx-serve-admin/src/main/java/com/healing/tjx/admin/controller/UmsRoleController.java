package com.healing.tjx.admin.controller;

import com.healing.tjx.admin.dto.UmsAdminChangeParam;
import com.healing.tjx.admin.dto.UmsRoleChangeParam;
import com.healing.tjx.admin.dto.UpdateStatusParam;
import com.healing.tjx.admin.entity.UmsRole;
import com.healing.tjx.admin.service.UmsRoleService;
import com.healing.tjx.common.api.BasicsResult;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @作者: tjx
 * @描述: 角色管理
 * @创建时间: 创建于10:25 2020-12-30
 **/
@Slf4j
@RestController
@RequestMapping("/ums/role")
@Api(tags = "UmsRoleController", description = "后台角色管理")
public class UmsRoleController {


    @Autowired
    private UmsRoleService umsRoleService;

    @ApiOperation(value = "角色列表")
    @ApiImplicitParam(paramType = "query", name = "name", value = "姓名搜索", dataType = "String")
    @GetMapping("/list")
    public PageResult<UmsRole> list(@Validated PageParam page, String name) {
        return umsRoleService.list(page, name);
    }


    @ApiOperation(value = "修改角色状态")
    @PostMapping("/updateStatus")
    public CommonResult<Long> list(@Validated @RequestBody UpdateStatusParam updateStatusParam) {
        return umsRoleService.updateUmsRoleStatus(updateStatusParam);
    }

    @ApiOperation(value = "新增/修改 角色")
    @PostMapping("/change")
    public BasicsResult change(@Validated @RequestBody UmsRoleChangeParam umsRoleChangeParam) {
        return umsRoleService.change(umsRoleChangeParam);
    }

    @ApiOperation(value = "删除角色")
    @PostMapping("/delete")
    public CommonResult delete(int id) {
        return umsRoleService.delete(id);
    }


}
