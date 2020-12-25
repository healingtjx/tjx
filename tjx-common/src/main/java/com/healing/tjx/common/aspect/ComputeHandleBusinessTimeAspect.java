package com.healing.tjx.common.aspect;

import com.healing.tjx.common.utils.ParametersUtil;
import com.healing.tjx.common.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @作者: tjx
 * @描述: 计算处理业务所需时间的切面日志
 * @创建时间: 创建于10:52 2020-12-22
 **/
@Slf4j
@Aspect
@Service
public class ComputeHandleBusinessTimeAspect {


    @Around("execution(public * com.healing.tjx.*.controller.*.*(..))")
    public Object timeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        //展示请求相关数据
        ParametersUtil.showRequestParams(request, joinPoint, log);
        //获取处理开始时间
        long startTime = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        //获取处理结束时间
        long endTime = System.currentTimeMillis();
        //获取方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        //展示日志
        ParametersUtil.showHandleBusinessTime(log, object, methodName, (endTime - startTime));
        return object;
    }

}