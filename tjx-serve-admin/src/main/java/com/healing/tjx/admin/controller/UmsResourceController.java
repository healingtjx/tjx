package com.healing.tjx.admin.controller;

import com.healing.tjx.admin.dto.AllocResult;
import com.healing.tjx.admin.dto.UmsResourceChangeParam;
import com.healing.tjx.admin.dto.UmsResourcePageParam;
import com.healing.tjx.admin.entity.UmsResource;
import com.healing.tjx.admin.service.UmsResourceService;
import com.healing.tjx.common.api.CommonResult;
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
 * @描述: 资源模块
 * @创建时间: 创建于10:19 2021-01-02
 **/
@Slf4j
@RestController
@RequestMapping("/ums/resource")
@Api(tags = "UmsResourceController", value = "后台资源管理")
public class UmsResourceController {

    @Autowired
    private UmsResourceService umsResourceService;


    @ApiOperation(value = "资源列表")
    @GetMapping("/list")
    public PageResult<UmsResource> list(@Validated UmsResourcePageParam umsResourcePageParam) {
        return umsResourceService.list(umsResourcePageParam);
    }


    @ApiOperation(value = "角色资源列表")
    @ApiImplicitParam(paramType = "query", name = "roleId", value = "角色id", dataType = "String", required = true)
    @GetMapping("/treeList")
    public CommonResult<AllocResult> treeList(int roleId) {
        return umsResourceService.treeList(roleId);
    }



    @ApiOperation(value = "新增/修改 资源")
    @PostMapping("/change")
    public CommonResult<Long> change(@Validated @RequestBody UmsResourceChangeParam umsResourceChangeParam) {
        return umsResourceService.change(umsResourceChangeParam);
    }

    @ApiOperation(value = "删除资源")
    @PostMapping("/delete")
    public CommonResult delete(int id) {
        return umsResourceService.delete(id);
    }


}
