package com.healing.tjx.common.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @Author: tjx
 * @Description: 分页请求参数 (全局统一)
 * @Date: 创建于10:34 2020-12-25
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PageParam {


    /**
     * 条数
     */
    @NotNull
    @ApiModelProperty(value = "条数", required = true)
    private Integer pageSize;
    /**
     * 页数
     */
    @NotNull
    @ApiModelProperty(value = "页数", required = true)
    private Integer pageIndex;


    /**
     * 生成 用于mybatis-plus 使用的参数
     *
     * @param <T>
     * @return
     */
    public <T> IPage<T> generatePagination() {
        return new Page<>(this.getPageIndex(), this.getPageSize(), true);
    }

}
