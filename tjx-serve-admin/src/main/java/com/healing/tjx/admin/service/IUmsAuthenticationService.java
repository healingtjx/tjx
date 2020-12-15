package com.healing.tjx.admin.service;

import com.healing.tjx.admin.dto.TokenResult;
import com.healing.tjx.admin.dto.UmsAdminLoginParam;
import com.healing.tjx.common.api.CommonResult;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 后台用户管理
 * @author tjx
 */
public interface IUmsAuthenticationService {

    /**
     *  用户登陆
     *
     * @param umsAdminLoginParam
     * @return CommonResult<TokenResult>
     */
    CommonResult<TokenResult> login(UmsAdminLoginParam umsAdminLoginParam);


    /**
     * 根据username 查询用户 【返回Security的user对象】
     *
     * @param username
     * @return UserDetails
     */
    UserDetails loadUserByUsername(String username);

}
