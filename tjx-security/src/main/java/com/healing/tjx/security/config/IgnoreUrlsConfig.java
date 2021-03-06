package com.healing.tjx.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: tjx
 * @Description: 对SpringSecurity的配置的扩展，用于配置白名单接口
 * @Date: 创建于9:54 2020-12-15
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();

}
