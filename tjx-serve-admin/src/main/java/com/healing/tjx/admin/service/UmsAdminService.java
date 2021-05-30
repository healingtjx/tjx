package com.healing.tjx.admin.service;

import com.healing.tjx.admin.dto.UmsAdminAssignParam;
import com.healing.tjx.admin.dto.UmsAdminChangeParam;
import com.healing.tjx.admin.dto.UmsAdminResult;
import com.healing.tjx.admin.dto.UpdateStatusParam;
import com.healing.tjx.admin.entity.UmsRole;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import com.healing.tjx.common.api.SortParam;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于10:59 2020-12-24
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
    PageResult<UmsAdminResult> list(PageParam pageParam, SortParam sortParam, String name);


    /**
     * 权限列表
     *
     * @param adminId 管理id
     * @return
     */
    PageResult<UmsRole> accreditList(int adminId);

    /**
     * 角色分配
     *
     * @param param
     * @return
     */
    CommonResult assign(UmsAdminAssignParam param);

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
    CommonResult<Long> updateUmsAdminStatus(UpdateStatusParam statusParam);
}
