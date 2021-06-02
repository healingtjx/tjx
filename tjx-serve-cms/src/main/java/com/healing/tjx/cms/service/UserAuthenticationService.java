package com.healing.tjx.cms.service;

import com.healing.tjx.datasource.entity.UserBase;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author: 作者
 * @Description: 登陆相关
 * @Date: 创建于22:33 2021-06-02
 **/
public interface UserAuthenticationService {

    /**
     * 根据 username 查询用用户（带缓存）【返回ums_admin表对象】
     *
     * @param userId 用户名
     * @returnUmsAdmin
     */
    UserBase getAdminByUserIdAndCache(String userId);


    /**
     * 根据username 查询用户 【返回Security的user对象】
     *
     * @param userId 用户id
     * @return UserDetails
     */
    UserDetails loadUserByUserId(String userId);
}

