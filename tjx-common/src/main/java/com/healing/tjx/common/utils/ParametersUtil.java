package com.healing.tjx.common.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;

/**
 * @作者: tjx
 * @描述: 获取参数工具类
 * @创建时间: 创建于15:51 2020-12-16
 **/
public class ParametersUtil {

    /**
     * 展示aop前置通知参数 (aop)
     * @param joinPoint
     * @return
     */
    public static void showAopBeforeParameters(JoinPoint joinPoint, Logger logger,String methodName) {
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
        logger.debug("------------"+methodName+"------------");
        for (int i = 0; i < paramNames.length; i++) {
            logger.debug("{}: {}",paramNames[i],paramValues[i]);
        }

    }

    public static void showAopAfterResult(Object result,Logger logger,String methodName){
        logger.debug("结果: {}",result);
        logger.debug("------------"+methodName+"------------");
    }

}
