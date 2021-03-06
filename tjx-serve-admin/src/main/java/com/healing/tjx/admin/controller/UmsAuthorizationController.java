package com.healing.tjx.admin.controller;

import com.healing.tjx.admin.dto.AdminInfoResult;
import com.healing.tjx.admin.dto.TokenResult;
import com.healing.tjx.admin.dto.UmsAdminLoginParam;
import com.healing.tjx.admin.service.UmsAuthenticationService;
import com.healing.tjx.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @Author: tjx
 * @Description: 用户认证模块
 * @Date: 创建于10:10 2020-12-14
 **/
@Slf4j
@RestController
@RequestMapping("/ums/authentication")
@Api(tags = "UmsAuthorizationController", value = "后台用户认证管理")
public class UmsAuthorizationController {


    @Autowired
    private UmsAuthenticationService iUmsAuthenticationService;


    @ApiOperation(value = "登录接口【返回token】")
    @PostMapping("/login")
    public CommonResult<TokenResult> login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        return iUmsAuthenticationService.login(umsAdminLoginParam);
    }

    @ApiOperation(value = "用户信息接口【返回用户信息】")
    @GetMapping("/info")
    public CommonResult<AdminInfoResult> userInfo(Principal principal) {
        if (principal == null) {
            return CommonResult.unauthorized(null);
        }
        return iUmsAuthenticationService.userInfo(principal.getName());
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public CommonResult logout(Principal principal) {
        if (principal == null) {
            return CommonResult.success();
        }
        return iUmsAuthenticationService.logout(principal.getName());
    }


}
