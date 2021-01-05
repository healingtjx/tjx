package com.healing.tjx.admin.controller;

import com.healing.tjx.admin.dto.AllocResult;
import com.healing.tjx.admin.dto.UmsMenuChangeParam;
import com.healing.tjx.admin.dto.UpdateStatusParam;
import com.healing.tjx.admin.entity.UmsMenu;
import com.healing.tjx.admin.service.UmsMenuService;
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
 * @描述:
 * @创建时间: 创建于10:19 2020-12-31
 **/
@Slf4j
@RestController
@RequestMapping("/ums/menu")
@Api(tags = "UmsMenuController", value = "后台菜单管理")
public class UmsMenuController {


    @Autowired
    private UmsMenuService umsMenuService;


    @ApiOperation(value = "菜单列表")
    @ApiImplicitParam(paramType = "query", name = "pid", value = "父菜单id(一级菜单传入0)", dataType = "String", required = true)
    @GetMapping("/list")
    public PageResult<UmsMenu> list(@Validated PageParam page, int pid) {
        return umsMenuService.list(page, pid);
    }


    @ApiOperation(value = "角色授权菜单列表")
    @ApiImplicitParam(paramType = "query", name = "roleId", value = "角色id", dataType = "String", required = true)
    @GetMapping("/treeList")
    public CommonResult<AllocResult> treeList(int roleId) {
        return umsMenuService.treeList(roleId);
    }



    @ApiOperation(value = "新增/修改 菜单")
    @PostMapping("/change")
    public CommonResult<Long> change(@Validated @RequestBody UmsMenuChangeParam umsMenuChangeParam) {
        return umsMenuService.change(umsMenuChangeParam);
    }

    @ApiOperation(value = "删除菜单")
    @PostMapping("/delete")
    public CommonResult delete(int id) {
        return umsMenuService.delete(id);
    }


    @ApiOperation(value = "修改菜单显示隐藏")
    @PostMapping("/updateHidden")
    public CommonResult<Long> list(@Validated @RequestBody UpdateStatusParam updateStatusParam) {
        return umsMenuService.updateUmsMenuHidden(updateStatusParam);
    }


}
