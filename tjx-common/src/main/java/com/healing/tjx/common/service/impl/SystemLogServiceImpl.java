package com.healing.tjx.common.service.impl;

import com.healing.tjx.common.service.SystemLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tjx
 * @Description 系统日志处理
 * @Date 2021/5/30 1:45 下午
 */
@Slf4j
@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Override
    public void recordLog(HttpServletRequest request, String methodName, Long time, JoinPoint joinPoint) {

    }
}
