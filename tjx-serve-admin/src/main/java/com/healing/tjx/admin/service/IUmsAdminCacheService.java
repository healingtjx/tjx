package com.healing.tjx.admin.service;

import com.healing.tjx.admin.entity.UmsAdmin;

/**
 * @作者: tjx
 * @描述: 后台用户相关缓存操作
 * @创建时间: 创建于16:46 2020-12-15
 **/
public interface IUmsAdminCacheService {

    /**
     * 用户登陆后缓存用户信息(24小时)
     *
     * @param umsAdmin
     */
    void setAdmin(UmsAdmin umsAdmin);

    /**
     * 根据username查询缓存
     *
     * @param username
     * @return
     */
    UmsAdmin getAdmin(String username);


    /**
     * 删除缓存
     *
     * @param username
     */
    void delAdmin(String username);
}
