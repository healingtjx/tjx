package com.healing.tjx.admin.controller;

import com.healing.tjx.common.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者: tjx
 * @描述:
 * @创建时间: 创建于15:11 2020-12-09
 **/
@Slf4j
@RestController
@RequestMapping("/")
public class TestIndex {

    @Autowired
    private RedisService redisService;

    @GetMapping
    public String test(){
        log.info("测试日志系统");
        redisService.set("tjx","我来了了");
        return "123";
    }
}
