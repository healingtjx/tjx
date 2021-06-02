package com.healing.tjx.cms.config;

import com.healing.tjx.cms.service.UserAuthenticationService;
import com.healing.tjx.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于10:12 2020-12-15
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CmsSecurityConfig extends SecurityConfig {

    @Autowired
    private UserAuthenticationService authenticationService;


    /**
     * 注入自己实现的 userDetailsService
     *
     * @return
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        //修改成自己的
        return userId -> authenticationService.loadUserByUserId(userId);
    }
}
