package com.healing.tjx.admin.listener;

import cn.hutool.json.JSONUtil;
import com.healing.tjx.admin.service.SysConfigService;
import com.healing.tjx.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author tjx
 * @Description tjx项目事件处理
 * @Date 2021/5/25 9:53 下午
 */
@Slf4j
@Component
public class TjxApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //容器启动事件
        if (applicationEvent instanceof ApplicationStartedEvent) {
            //刷新配置oss文件上传
            CommonResult result = sysConfigService.configOSS();
            log.info("刷新文件上传配置:{}", JSONUtil.toJsonStr(result));
        }
    }
}
