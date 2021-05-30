package com.healing.tjx.admin.controller;

import com.healing.tjx.admin.service.SysLogService;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 作者
 * @Description: 系统日志
 * @Date: 创建于14:45 2021-05-30
 **/
@RestController
@RequestMapping("/sys/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @ApiOperation(value = "日志列表")
    @GetMapping("/list")
    public PageResult list(@Validated PageParam param, String name) {
        return sysLogService.list(param, name);
    }

}

