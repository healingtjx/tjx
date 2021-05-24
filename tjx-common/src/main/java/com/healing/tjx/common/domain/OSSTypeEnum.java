package com.healing.tjx.common.domain;

/**
 * @author tjx
 * @Description 文件上传类型
 * @Date 2021/5/23 10:33 下午
 */
public enum OSSTypeEnum {
    /**
     * 阿里云
     */
    ALIYUN(0),
    /**
     * 七牛云
     */
    QINIUYUN(1),
    /**
     * 腾讯云
     */
    QYUN(2);

    private int type;

    OSSTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
