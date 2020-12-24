package com.healing.tjx.admin.service;

import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.common.api.PageResult;

/**
 * @作者: tjx
 * @描述:
 * @创建时间: 创建于10:59 2020-12-24
 **/
public interface UmsAdminService {

    /**
     * 管理员列表
     * @return CommonResult<UmsAdmin>
     */
    PageResult<UmsAdmin> list();
}
