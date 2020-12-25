package com.healing.tjx.common.utils;

import cn.hutool.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @作者: tjx
 * @描述: 获取参数工具类
 * @创建时间: 创建于15:51 2020-12-16
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

    /**
     * 展示请求相关的参数
     *
     * @param request
     */
    public static void showRequestParams(HttpServletRequest request, JoinPoint joinPoint, Logger logger) {

        //获取ip
        String currentUrl = request.getRequestURI();
        String ctxPath = request.getContextPath();
        String targetUrl = currentUrl.substring(ctxPath.length() + 1);
        String ip = WebUtil.getRemoteAddrIp(request);
        //展示请求基本信息
        logger.info("------------------------------请求开始------------------------------");
        logger.info("接口地址:" + targetUrl);
        logger.info("ip地址:" + ip);
        //展示请求参数
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            logger.debug("{} : {}", paramNames[i], JSONUtil.toJsonStr(paramValues[i]));
        }
    }


    /**
     * 展示结算结果
     *
     * @param result     结果
     * @param logger     日志对象
     * @param methodName 方法命
     */
    public static void showHandleBusinessTime(Logger logger, Object result, String methodName, long time) {
        logger.debug("----- 返回结果: {}", JSONUtil.toJsonStr(result));
        logger.info("-----{} 方式执行耗时: {} ms", methodName, time);
        logger.info("------------------------------请求结束------------------------------");
    }
}
