package com.healing.tjx.common.service;

import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tjx
 * @Description 记录操作日志
 * @Date 2021/5/30 1:36 下午
 */
public interface SystemLogService {

    /**
     * 记录日志
     *
     * @param request    接口详情
     * @param methodName 方法名称
     * @param time       耗时
     */
    void recordLog(HttpServletRequest request, String methodName, Long time, JoinPoint joinPoint);

}
