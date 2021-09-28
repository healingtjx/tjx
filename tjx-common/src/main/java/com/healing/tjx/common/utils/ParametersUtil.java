package com.healing.tjx.common.utils;

import cn.hutool.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: tjx
 * @Description: 获取参数工具类
 * @Date: 创建于15:51 2020-12-16
 **/
public class ParametersUtil {

    /**
     * 展示aop前置通知参数 (aop)
     *
     * @param joinPoint
     * @return
     */
    public static void showAopBeforeParameters(JoinPoint joinPoint, Logger logger, String methodName) {
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        logger.debug("==> methodName: {}", methodName);
        for (int i = 0; i < paramNames.length; i++) {
            logger.debug("==> Parameter:{}(参数): {}(实际值)", paramNames[i], paramValues[i]);
        }
    }

    /**
     * 展示结果
     *
     * @param result
     * @param logger
     */
    public static void showAopAfterResult(Object result, Logger logger) {
        logger.debug("<==  结果{}", result);

    }
}
