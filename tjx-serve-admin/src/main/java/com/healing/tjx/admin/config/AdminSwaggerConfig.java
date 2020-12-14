package com.healing.tjx.admin.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.healing.tjx.common.config.BaseSwaggerConfig;
import com.healing.tjx.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @作者: tjx
 * @描述:
 * @创建时间: 创建于11:09 2020-12-14
 **/
@Configuration
@EnableSwagger2
@EnableKnife4j
public class AdminSwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.healing.tjx.admin.controller")
                .title("tjx后台系统")
                .description("tjx后台相关接口文档")
                .contactName("tjx")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }


}
