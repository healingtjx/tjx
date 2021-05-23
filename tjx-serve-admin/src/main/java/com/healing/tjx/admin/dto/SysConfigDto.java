package com.healing.tjx.admin.dto;

import com.healing.tjx.common.oss.CloudStorageConfig;
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
public class SysConfigDto extends CloudStorageConfig {
    /**
     * 当前选中 0 七牛云 1阿里云 2 腾讯云
     */
    @Range(min = 0, max = 2, message = "类型错误")
    @NotNull
    private Integer type;
}
