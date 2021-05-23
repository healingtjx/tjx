package com.healing.tjx.common.domain;

/**
 * @author tjx
 * @Description 管理系统配置
 * @Date 2021/5/18 9:32 下午
 */
public enum SysConfigEnum {
    /**
     * OSS配置
     */
    OSS_CONFIG("OSS_CONFIG");

    private String key;

    SysConfigEnum(String key) {
        this.key = key;
    }
}
