package com.healing.tjx.cms.config;

import com.healing.tjx.common.version.CustomRequestMappingHandlerMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author tjx
 * @Description 版本控制
 * @Date 2021/6/5 4:24 下午
 */
@Configuration
public class CmsWebVersionConfig implements WebMvcRegistrations {

    /**
     * 配置api版本权限管理
     *
     * @return
     */
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new CustomRequestMappingHandlerMapping();
    }

}
