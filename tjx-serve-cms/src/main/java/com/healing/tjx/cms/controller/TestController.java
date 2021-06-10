package com.healing.tjx.cms.controller;

import cn.hutool.json.JSONUtil;
import com.healing.tjx.cms.dto.TokenResult;
import com.healing.tjx.cms.dto.UserBaseDetails;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.version.ApiVersion;
import com.healing.tjx.datasource.entity.UserBase;
import com.healing.tjx.security.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://tjxcold.chsgw.com 快速生成个人模板
 *
 * @Author: 作者
 * @Description: 描述
 * @Date: 创建于22:18 2021-06-02
 **/
@RestController
@RequestMapping("/{version}/Test")
@Api(tags = "TestController", value = "用户系统配置")
public class TestController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @ApiVersion(1)
    @ApiOperation(value = "xx列表")
    @GetMapping("/list")
    public String list1() {
        return "版本控制";
    }


    @ApiVersion(2)
    @ApiOperation(value = "xx列表")
    @GetMapping("/list")
    public String list2() {
        UserBase userBase = new UserBase();
        userBase.setId(1);
        userBase.setSalt("fuckyopu");
        UserDetails userDetails = new UserBaseDetails(userBase);
        String token = jwtTokenUtil.generateToken(userDetails);
        TokenResult tokenResult = new TokenResult();
        tokenResult.setToken(token);
        tokenResult.setTokenHeader(jwtTokenUtil.getTokenHead());
        System.out.println(JSONUtil.toJsonStr(tokenResult));
        return "test";
    }


    @ApiOperation(value = "")
    @PostMapping("/")
    public CommonResult list() {
        return null;
    }

}

