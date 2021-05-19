package com.healing.tjx.admin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author tjx
 * @Description 用于oss配置结果，和oss配置参数
 * @Date 2021/5/18 9:26 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysConfigDto {
    /**
     * 当前选中 0 七牛云 1阿里云 2 腾讯云
     */
    @Range(min = 0, max = 2, message = "类型错误")
    @NotNull
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
