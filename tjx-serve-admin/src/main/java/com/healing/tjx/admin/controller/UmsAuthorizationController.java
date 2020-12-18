package com.healing.tjx.admin.controller;

import com.healing.tjx.admin.dto.AdminInfoResult;
import com.healing.tjx.admin.dto.TokenResult;
import com.healing.tjx.admin.dto.UmsAdminLoginParam;
import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.admin.service.IUmsAuthenticationService;
import com.healing.tjx.common.api.BasicsResult;
import com.healing.tjx.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @ApiOperation(value = "用户信息接口【返回用户信息】")
    @GetMapping("/info")
    public CommonResult<AdminInfoResult> userInfo(Principal principal){
        if(principal == null){
            return CommonResult.unauthorized(null);
        }
        String name = principal.getName();
        UmsAdmin admin = iUmsAuthenticationService.getAdminByUsernameAndCache(name);
        //封装结果
        AdminInfoResult adminInfoResult = new AdminInfoResult();
        adminInfoResult.setUsername(admin.getUsername());
        return CommonResult.success(adminInfoResult);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }


    @ApiModelProperty(value = "测试")
    @GetMapping("/test")
    public CommonResult test(){
        return CommonResult.success();
    }





}
