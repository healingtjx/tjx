package com.healing.tjx.admin.controller;

import com.healing.tjx.admin.dto.UmsResourceCategoryChangeParam;
import com.healing.tjx.admin.service.UmsResourceCategoryService;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import com.healing.tjx.datasource.entity.UmsResourceCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tjx
 * @Description: 资源分类管理
 * @Date: 创建于10:14 2021-01-04
 **/
@Slf4j
@RestController
@RequestMapping("/ums/resourceCategory")
@Api(tags = "UmsResourceCategoryController", value = "后台资源分类管理")
public class UmsResourceCategoryController {


    @Autowired
    private UmsResourceCategoryService umsResourceCategoryService;

    @ApiOperation(value = "资源分类列表")
    @GetMapping("/list")
    public PageResult<UmsResourceCategory> list(@Validated PageParam pageParam, String name) {
        return umsResourceCategoryService.list(pageParam, name);
    }


    @ApiOperation(value = "新增/修改 资源分类")
    @PostMapping("/change")
    public CommonResult<Long> change(@Validated @RequestBody UmsResourceCategoryChangeParam changeParam) {
        return umsResourceCategoryService.change(changeParam);
    }

    @ApiOperation(value = "删除资源分类")
    @PostMapping("/delete")
    public CommonResult delete(int id) {
        return umsResourceCategoryService.delete(id);
    }

}
