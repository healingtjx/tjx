package com.healing.tjx.admin.service;

import com.healing.tjx.admin.dto.AllocResult;
import com.healing.tjx.admin.dto.UmsResourceChangeParam;
import com.healing.tjx.admin.dto.UmsResourcePageParam;
import com.healing.tjx.admin.entity.UmsResource;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageResult;

/**
 * @作者: tjx
 * @描述: 资源模块
 * @创建时间: 创建于10:19 2021-01-02
 **/
public interface UmsResourceService {

    /**
     * 资源列表
     *
     * @param page
     * @return PageResult<UmsResource> 资源列表
     */
    PageResult<UmsResource> list(UmsResourcePageParam page);


    /**
     * 角色资源列表
     *
     * @param roleId
     * @return
     */
    CommonResult<AllocResult> treeList(int roleId);


    /**
     * 修改资源
     *
     * @param umsResourceChangeParam
     * @return CommonResult<Long> id
     */
    CommonResult<Long> change(UmsResourceChangeParam umsResourceChangeParam);


    /**
     * 删除资源
     *
     * @param id
     * @return CommonResult<Long> 资源编号
     */
    CommonResult<Long> delete(Integer id);

}
