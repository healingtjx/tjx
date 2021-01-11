package com.healing.tjx.admin.service;

import com.healing.tjx.admin.dto.UmsRoleAllocParam;
import com.healing.tjx.admin.dto.UmsRoleChangeParam;
import com.healing.tjx.admin.dto.UpdateStatusParam;
import com.healing.tjx.admin.entity.UmsRole;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于10:27 2020-12-30
 **/
public interface UmsRoleService {


    /**
     * 管理员列表 (支持姓名搜索)
     *
     * @param pageParam 分页参数
     * @param name      姓名 用于搜索
     * @return CommonResult<UmsAdmin>
     */
    PageResult<UmsRole> list(PageParam pageParam, String name);

    /**
     * 修改角色状态
     *
     * @param updateStatusParam
     * @return CommonResult<Long> id
     */
    CommonResult<Long> updateUmsRoleStatus(UpdateStatusParam updateStatusParam);


    /**
     * 修改校色
     *
     * @param umsRoleChangeParam
     * @return CommonResult<Long> id
     */
    CommonResult<Long> change(UmsRoleChangeParam umsRoleChangeParam);


    /**
     * 删除角色
     *
     * @param id
     * @return CommonResult
     */
    @Transactional(rollbackFor = Exception.class)
    CommonResult delete(Integer id);


    /**
     * 给角色赋予菜单权限 （两个以上的修改或者新增需要加上事务）
     *
     * @param allocParam
     * @return CommonResult
     */
    @Transactional(rollbackFor = Exception.class)
    CommonResult allocMenu(UmsRoleAllocParam allocParam);


    /**
     * 给角色赋予资源权限 （两个以上的修改或者新增需要加上事务）
     *
     * @param allocParam
     * @return CommonResult
     */
    @Transactional(rollbackFor = Exception.class)
    CommonResult allocResource(UmsRoleAllocParam allocParam);
}
