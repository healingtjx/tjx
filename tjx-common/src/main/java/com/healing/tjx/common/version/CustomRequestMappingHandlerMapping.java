package com.healing.tjx.common.version;


import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author tjx
 * @Description 自定义 RequestMappingHandlerMapping 处理版本控制
 * @Date 2021/6/5 4:13 下午
 */
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<ApiVersionCondition> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        NotNull notNull = AnnotationUtils.findAnnotation(handlerType, NotNull.class);
        return createCondition(apiVersion, notNull);
    }

    @Override
    protected RequestCondition<ApiVersionCondition> getCustomMethodCondition(Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        NotNull notNull = AnnotationUtils.findAnnotation(method, NotNull.class);
        return createCondition(apiVersion, notNull);
    }


    /**
     * 实例化RequestCondition
     *
     * @param apiVersion
     * @param notNull
     * @return
     */
    private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion, NotNull notNull) {
        return apiVersion == null ? null : new ApiVersionCondition(apiVersion.value(), notNull == null ? null : notNull.values());
    }

}