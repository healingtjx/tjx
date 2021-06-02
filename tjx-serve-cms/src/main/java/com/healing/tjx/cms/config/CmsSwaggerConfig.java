package com.healing.tjx.cms.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.healing.tjx.common.config.BaseSwaggerConfig;
import com.healing.tjx.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于11:09 2020-12-14
 **/
@Configuration
@EnableSwagger2
@EnableKnife4j
public class CmsSwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.healing.tjx.cms.controller")
                .title("tjx-cms管理客户端接口")
                .description("tjx-cms管理客户端接口文档")
                .contactName("tjx")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }


}
