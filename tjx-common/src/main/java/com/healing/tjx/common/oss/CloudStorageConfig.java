package com.healing.tjx.common.oss;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tjx
 * @Description 云存储配置信息
 * @Date 2021/5/23 10:13 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CloudStorageConfig {

    /**
     * 当前选中 0 七牛云 1阿里云 2 腾讯云
     */
    private Integer type;
    /**
     * 七牛云相关
     */
    private String qiniuDomain;
    private String qiniuPrefix;
    private String qiniuAccessKey;
    private String qiniuSecretKey;
    private String qiniuBucketName;
    /**
     * 阿里云相关
     */
    private String aliyunDomain;
    private String aliyunPrefix;
    private String aliyunEndPoint;
    private String aliyunAccessKeyId;
    private String aliyunAccessKeySecret;
    private String aliyunBucketName;

    /**
     * 腾讯相关
     */
    private String qcloudDomain;
    private String qcloudPrefix;
    private String qcloudAppId;
    private String qcloudSecretId;
    private String qcloudSecretKey;
    private String qcloudBucketName;
    private String qcloudRegion;
}
