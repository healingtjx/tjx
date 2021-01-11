package com.healing.tjx.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.healing.tjx.admin.dto.UmsAdminChangeParam;
import com.healing.tjx.admin.dto.UpdateStatusParam;
import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.admin.service.UmsAdminService;
import com.healing.tjx.common.api.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tjx
 * @Description: 管理用户操作
 * @Date: 创建于10:57 2020-12-24
 **/
@Slf4j
@RestController
@RequestMapping("/ums/admin")
@Api(tags = "UmsAdminController", value = "管理用户模块")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;

    @ApiOperation(value = "管理员列表")
    @ApiImplicitParam(paramType = "query", name = "name", value = "姓名搜索", dataType = "String")
    @GetMapping("/list")
    public PageResult<UmsAdmin> list(@Validated PageParam page, SortParam sortParam, String name) {
        return umsAdminService.list(page, sortParam, name);
    }


    @ApiOperation(value = "新增管理员/修改管理员")
    @PostMapping("/change")
    public BasicsResult change(@Validated @RequestBody UmsAdminChangeParam umsAdminChangeParam) {
        //手动校验 新增情况下必须要有密码
        if (umsAdminChangeParam.getId() == null) {
            if (StrUtil.isEmpty(umsAdminChangeParam.getPassword())) {
                return CommonResult.validateFailed("新增:password不能为空");
            }
        }
        return umsAdminService.change(umsAdminChangeParam);
    }


    @ApiOperation(value = "修改管理员状态")
    @PostMapping("/updateStatus")
    public CommonResult<Long> list(@Validated @RequestBody UpdateStatusParam umsAdminUpdateStatusParam) {
        return umsAdminService.updateUmsAdminStatus(umsAdminUpdateStatusParam);
    }

    @ApiOperation(value = "删除管理员")
    @PostMapping("/delete")
    public CommonResult delete(int id) {
        return umsAdminService.delete(id);
    }


}
