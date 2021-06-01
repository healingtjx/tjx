package com.healing.tjx.admin.service;

import com.healing.tjx.admin.dto.AllocResult;
import com.healing.tjx.admin.dto.UmsMenuChangeParam;
import com.healing.tjx.admin.dto.UpdateStatusParam;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import com.healing.tjx.datasource.entity.UmsMenu;

/**
 * @Author: tjx
 * @Description: 菜单管理
 * @Date: 创建于10:22 2020-12-31
 **/
public interface UmsMenuService {

    /**
     * 菜单列表
     *
     * @param page
     * @param pid  父类id
     * @return PageResult<UmsMenu>
     */
    PageResult<UmsMenu> list(PageParam page, int pid);


    /**
     * 角色授权菜单列表
     *
     * @param roleId 角色id
     * @return PageResult<UmsMenu>
     */
    CommonResult<AllocResult> treeList(int roleId);


    /**
     * 新增/修改 列表
     *
     * @param umsMenuChangeParam
     * @return CommonResult<Long> id
     */
    CommonResult<Long> change(UmsMenuChangeParam umsMenuChangeParam);


    /**
     * 修改菜单显示/隐藏
     *
     * @param updateStatusParam
     * @return CommonResult<Long> id
     */
    CommonResult<Long> updateUmsMenuHidden(UpdateStatusParam updateStatusParam);

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    CommonResult delete(int id);


}
