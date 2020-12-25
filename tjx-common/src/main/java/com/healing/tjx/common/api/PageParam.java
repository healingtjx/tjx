package com.healing.tjx.common.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @作者: tjx
 * @描述: 分页请求参数 (全局统一)
 * @创建时间: 创建于10:34 2020-12-25
 **/
@Getter
@Setter
public class PageParam {

    /**
     * 条数
     */
    @NotEmpty
    @ApiModelProperty(value = "条数", required = true)
    private int pageSize;
    /**
     * 页数
     */
    @NotEmpty
    @ApiModelProperty(value = "页数", required = true)
    private int pageIndex;

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
