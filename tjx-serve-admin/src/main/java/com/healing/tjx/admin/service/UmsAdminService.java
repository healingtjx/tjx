package com.healing.tjx.admin.service;

import com.healing.tjx.admin.dto.UmsAdminChangeParam;
import com.healing.tjx.admin.dto.UmsAdminUpdateStatusParam;
import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import com.healing.tjx.common.api.SortParam;

/**
 * @作者: tjx
 * @描述:
 * @创建时间: 创建于10:59 2020-12-24
 **/
public interface UmsAdminService {

    /**
     * 管理员列表 (支持姓名搜索)
     *
     * @param pageParam 分页参数
     * @param sortParam 用于排序
     * @param name      姓名 用于搜索
     * @return CommonResult<UmsAdmin>
     */
    PageResult<UmsAdmin> list(PageParam pageParam, SortParam sortParam, String name);


    /**
     * 修改/新增 管理员
     *
     * @param changeParam 参数
     * @return CommonResult<Integer> 用户id
     */
    CommonResult<Long> change(UmsAdminChangeParam changeParam);


    /**
     * 删除 管理员
     *
     * @param id
     * @return
     */
    CommonResult<Integer> delete(int id);

    /**
     * 修改管理员状态
     *
     * @param statusParam 修改参数
     * @return CommonResult<Integer> 用户id
     */
    CommonResult<Long> updateUmsAdminStatus(UmsAdminUpdateStatusParam statusParam);
}
