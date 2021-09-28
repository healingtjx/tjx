package com.healing.tjx.common.config;

import com.healing.tjx.common.interceptor.BaseInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: tjx
 * @Description: 基本配置
 * @Date: 创建于11:08 2021-09-28
 **/
@Configuration
public class BaseConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //基础参数拦截器
        registry.addInterceptor(new BaseInterceptor()).addPathPatterns("/*/**");
    }
}
