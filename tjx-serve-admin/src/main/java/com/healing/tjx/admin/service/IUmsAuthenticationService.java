package com.healing.tjx.admin.service;

import com.healing.tjx.admin.dto.TokenResult;
import com.healing.tjx.admin.dto.UmsAdminLoginParam;
import com.healing.tjx.common.api.CommonResult;


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

}
