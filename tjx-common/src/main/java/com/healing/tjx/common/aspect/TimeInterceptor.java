package com.healing.tjx.common.aspect;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.healing.tjx.common.api.BasicsResult;
import com.healing.tjx.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 检测方法执行耗时的spring切面类
 * 使用@Aspect注解的类，Spring将会把它当作一个特殊的Bean（一个切面），也就是不对这个类本身进行动态代理
 *
 * @author tjx
 * @date 2018-06-05
 */
@Slf4j
@Aspect
@Component
public class TimeInterceptor {

    /**
     * service层的统计耗时切面，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
     */
    public static final String POINT = "execution (* com.healing.tjx.*.service.impl.*.*(..))";

    /**
     * 统计方法执行耗时Around环绕通知
     *
     * @param joinPoint
     * @return
     */
    @Around(POINT)
    public Object timeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 定义返回对象、得到方法需要的参数
        Object obj ;
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        try {
            obj = joinPoint.proceed(args);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            if(e instanceof ApiException){
            }else {
                log.error("统计某方法执行耗时环绕通知出错: {}", e.getMessage(), e);
            }
            this.printExecTime(methodName, BasicsResult.failed(e.getMessage()), startTime, endTime);
            throw e;
        }

        // 获取执行的方法名  
        long endTime = System.currentTimeMillis();
        // 打印耗时的信息
        this.printExecTime(methodName, obj,startTime, endTime);
        return obj;
    }

    /**
     * 打印方法执行耗时的信息
     *
     * @param methodName
     * @param startTime
     * @param endTime
     */
    private void printExecTime(String methodName,Object object, long startTime, long endTime) {
        long diffTime = endTime - startTime;
        log.debug("result:{}",JSONUtil.toJsonStr(object));
        log.info("-----" + methodName + " 方法执行耗时：" + diffTime + " ms");
    }


}