package com.healing.tjx.admin.service;

import com.healing.tjx.admin.dto.UmsResourceCategoryChangeParam;
import com.healing.tjx.admin.entity.UmsResourceCategory;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;

/**
 * @作者: tjx
 * @描述: 资源分类管理
 * @创建时间: 创建于10:14 2021-01-04
 **/
public interface UmsResourceCategoryService {

    /**
     * 资源分类列表
     *
     * @param page
     * @param name 名称搜索
     * @return PageResult<UmsResourceCategory> 资源列表
     */
    PageResult<UmsResourceCategory> list(PageParam page, String name);


    /**
     * 修改分类资源
     *
     * @param changeParam
     * @return CommonResult<Long> id
     */
    CommonResult<Long> change(UmsResourceCategoryChangeParam changeParam);


    /**
     * 删除资源分类
     *
     * @param id
     * @return CommonResult<Long> 资源编号
     */
    CommonResult<Long> delete(Integer id);

}
