package com.healing.tjx.security.aspect;

import com.healing.tjx.common.utils.ParametersUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

/**
 * @Author: tjx
 * @Description: redis缓存切面
 * @Date: 创建于15:28 2020-12-16
 **/
@Slf4j
@Aspect
@Service
public class RedisCacheAspect {


    /**
     * 环绕通知  拦截 redis所有操作
     *
     * @param joinPoint
     * @return Object
     * @throws Throwable
     */
    @Around("execution(public * com.healing.tjx.common.service.RedisService.*(..))")
    public Object showParametersAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法名字
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        //前置显示 参数
        ParametersUtil.showAopBeforeParameters(joinPoint, log, methodName);
        //执行连接点方法，object：方法返回值
        Object object = joinPoint.proceed();
        //后置 显示结果
        ParametersUtil.showAopAfterResult(object, log);
        return object;
    }

}
