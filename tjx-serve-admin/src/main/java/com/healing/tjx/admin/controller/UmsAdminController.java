package com.healing.tjx.admin.controller;

import cn.hutool.json.JSONUtil;
import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.admin.service.UmsAdminService;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者: tjx
 * @描述: 管理用户操作
 * @创建时间: 创建于10:57 2020-12-24
 **/
@Slf4j
@RestController
@RequestMapping("/ums/admin")
@Api(tags = "UmsAdminController", description = "管理用户模块")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;

    @ApiOperation(value = "管理员列表")
    @ApiImplicitParam(paramType = "query", name = "name", value = "姓名搜索", dataType = "String")
    @GetMapping("/list")
    public PageResult<UmsAdmin> list(PageParam page, String name) {
        log.debug("page:{}", JSONUtil.toJsonStr(page));
        log.debug("name:{}", name);
        return umsAdminService.list(page,name);
    }

}
