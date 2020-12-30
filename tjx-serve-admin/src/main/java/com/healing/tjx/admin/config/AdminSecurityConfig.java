package com.healing.tjx.admin.config;

import com.healing.tjx.admin.service.UmsAuthenticationService;
import com.healing.tjx.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @作者: tjx
 * @描述:
 * @创建时间: 创建于10:12 2020-12-15
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminSecurityConfig extends SecurityConfig {

    @Autowired
    private UmsAuthenticationService authenticationService;


    /**
     * 注入自己实现的 userDetailsService
     *
     * @return
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        //修改成自己的
        return username -> authenticationService.loadUserByUsername(username);
    }
}
