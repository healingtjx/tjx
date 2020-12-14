package com.healing.tjx.admin.controller;

import com.healing.tjx.admin.dto.TokenResult;
import com.healing.tjx.admin.dto.UmsAdminLoginParam;
import com.healing.tjx.admin.service.IUmsAuthenticationService;
import com.healing.tjx.common.api.BasicsResult;
import com.healing.tjx.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者: tjx
 * @描述: 用户认证模块
 * @创建时间: 创建于10:10 2020-12-14
 **/
@Slf4j
@RestController
@RequestMapping("/admin/authentication")
@Api(tags = "UmsAuthorizationController", description = "后台用户认证管理")
public class UmsAuthorizationController {


    @Autowired
    private IUmsAuthenticationService iUmsAuthenticationService;


    @ApiOperation(value = "登录接口【返回token】")
    @PostMapping("/login")
    public CommonResult<TokenResult> login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam){
        return iUmsAuthenticationService.login(umsAdminLoginParam);
    }






}
