package com.healing.tjx.datasource.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于14:10 2020-12-10
 **/
@Configuration
@MapperScan("com.healing.tjx.datasource.mapper")
public class MapperConfig {

    /**
     * 配置 mybatis-plus 分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
