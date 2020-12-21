package com.healing.tjx.security.config;

import com.healing.tjx.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @作者: tjx
 * @描述: redis 配置
 * @创建时间: 创建于16:26 2020-12-09
 **/
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
