package com.healing.tjx.cms.controller;

import com.healing.tjx.cms.dto.WxAuthorizationParam;
import com.healing.tjx.cms.service.UserAuthenticationService;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.version.ApiVersion;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tjx
 * @Description: 用户授权
 * @Date: 创建于22:02 2021-06-10
 **/
@RestController
@RequestMapping("/{version}/auth")
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @ApiVersion(1)
    @ApiOperation(value = "微信小程序登陆授权")
    @PostMapping("/applet")
    public CommonResult appletAuth(@Validated WxAuthorizationParam param) {
        return userAuthenticationService.appletAuth(param);
    }
}

