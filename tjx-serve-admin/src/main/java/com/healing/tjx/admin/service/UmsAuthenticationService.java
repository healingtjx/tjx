package com.healing.tjx.admin.service;

import com.healing.tjx.admin.dto.AdminInfoResult;
import com.healing.tjx.admin.dto.TokenResult;
import com.healing.tjx.admin.dto.UmsAdminLoginParam;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.datasource.entity.UmsAdmin;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 后台用户管理
 *
 * @author tjx
 */
public interface UmsAuthenticationService {


    /**
     * 根据 username 查询用用户（带缓存）【返回ums_admin表对象】
     *
     * @param username 用户名
     * @returnUmsAdmin
     */
    UmsAdmin getAdminByUsernameAndCache(String username);


    /**
     * 用户登陆
     *
     * @param umsAdminLoginParam 用户名和密码
     * @return CommonResult<TokenResult>
     */
    CommonResult<TokenResult> login(UmsAdminLoginParam umsAdminLoginParam);


    /**
     * 根据username 查询用户 【返回Security的user对象】
     *
     * @param username 用户名
     * @return UserDetails
     */
    UserDetails loadUserByUsername(String username);


    /**
     * 用户信息
     *
     * @param username 用户名
     * @return CommonResult<AdminInfoResult> 用户信息和菜单
     */
    CommonResult<AdminInfoResult> userInfo(String username);


    /**
     * 登出(删除redis信息)
     *
     * @param username 用户名
     * @return CommonResult success
     */
    CommonResult logout(String username);
}
