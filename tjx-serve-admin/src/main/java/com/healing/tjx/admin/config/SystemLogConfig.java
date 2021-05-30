package com.healing.tjx.admin.config;

import com.healing.tjx.admin.service.impl.AdminSystemLogServiceImpl;
import com.healing.tjx.common.service.SystemLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author tjx
 * @Description 系统日志配置
 * @Date 2021/5/30 1:51 下午
 */
@Slf4j
@Configuration
public class SystemLogConfig {

    @Resource
    private AdminSystemLogServiceImpl adminSystemLogService;

    @Bean
    public SystemLogService systemLogService() {
        return (request, methodName, time, joinPoint) -> adminSystemLogService.recordLog(request, methodName, time, joinPoint);
    }
}
