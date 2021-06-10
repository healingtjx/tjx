package com.healing.tjx.cms.service;

import com.healing.tjx.datasource.entity.UserBase;

/**
 * @Author: 作者
 * @Description: 描述
 * @Date: 创建于22:44 2021-06-02
 **/
public interface UserBaseCacheService {

    /**
     * 用户登陆后缓存用户信息(24小时)
     *
     * @param userBase
     */
    void setUser(UserBase userBase);

    /**
     * 根据userId查询缓存
     *
     * @param userId
     * @return
     */
    UserBase getUser(String userId);


    /**
     * 删除缓存
     *
     * @param userId
     */
    void delUser(String userId);


    /**
     * 根据code 获取 SessionKey
     *
     * @param code
     * @return
     */
    String getSessionKey(String code);

    /**
     * 设置 SessionKey
     *
     * @param code
     * @param sessionKey
     */
    void setSessionKey(String code, String sessionKey);
}

