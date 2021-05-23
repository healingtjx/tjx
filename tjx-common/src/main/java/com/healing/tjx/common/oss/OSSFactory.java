package com.healing.tjx.common.oss;

import com.healing.tjx.common.domain.OSSTypeEnum;
import com.healing.tjx.common.domain.SysConfigEnum;
import com.healing.tjx.common.service.RedisService;
import com.healing.tjx.common.utils.SpringContextUtils;

/**
 * @author tjx
 * @Description 文件上传Factory
 * @Date 2021/5/23 9:55 下午
 */
public final class OSSFactory {
    /**
     * redis 注入
     */
    private static RedisService redisService;

    static {
        OSSFactory.redisService = (RedisService) SpringContextUtils.getBean("redisService");
    }

    /**
     * 获取上传对象
     *
     * @return
     */
    public static CloudStorageService build() {
        //获取redis存入的配置信息
        CloudStorageConfig storageConfig = (CloudStorageConfig) redisService.get(SysConfigEnum.OSS_CONFIG.toString());
        if (storageConfig != null) {
            //根据不同类型转化成不同上传对象
            if (storageConfig.getType().intValue() == OSSTypeEnum.ALIYUN.getType()) {
                return new AliyunCloudStorageService(storageConfig);
            }
        }

        return null;
    }
}
