package com.healing.tjx.common.version;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping

/**
 * @Author: tjx
 * @Description: 版本控制注解
 * @Date: 创建于 04:06 2021-06-05
 **/
public @interface ApiVersion {
    int value();
}

