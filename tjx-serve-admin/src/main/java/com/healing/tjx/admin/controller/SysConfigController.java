package com.healing.tjx.admin.controller;


import com.healing.tjx.admin.dto.SysConfigDto;
import com.healing.tjx.admin.service.SysConfigService;
import com.healing.tjx.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: tjx
 * @Description: 用户系统配置
 * @Date: 创建于 11:05 2021-05-17
 **/
@Slf4j
@RestController
@RequestMapping("/sys/config")
@Api(tags = "SysConfigController", value = "用户系统配置")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    @ApiOperation(value = "获取配置")
    @GetMapping("/config")
    public CommonResult config() {
        return sysConfigService.config();
    }

    @ApiOperation(value = "保存配置")
    @PostMapping("/save")
    public CommonResult save(@Validated @RequestBody SysConfigDto param) {
        return sysConfigService.save(param);
    }

}
