package com.healing.tjx.admin.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.healing.tjx.admin.bo.AdminUserDetails;
import com.healing.tjx.admin.dto.TokenResult;
import com.healing.tjx.admin.dto.UmsAdminLoginParam;
import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.admin.mapper.UmsAdminMapper;
import com.healing.tjx.admin.service.IUmsAdminCacheService;
import com.healing.tjx.admin.service.IUmsAuthenticationService;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.exception.Asserts;
import com.healing.tjx.security.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author tjx
 */
@Slf4j
@Service
public class UmsAuthenticationServiceImpl implements IUmsAuthenticationService {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UmsAdminMapper umsAdminMapper;


    @Autowired
    private IUmsAdminCacheService iUmsAdminCacheService;


    @Override
    public UmsAdmin getAdminByUsernameAndCache(String username) {
        //如果缓存有就直接返回缓存
        UmsAdmin admin = iUmsAdminCacheService.getAdmin(username);
        if(admin != null){
            return admin;
        }
        //如果没有就重新查询
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<UmsAdmin>()
                .eq("username",username);
        UmsAdmin umsAdmin = umsAdminMapper.selectOne(queryWrapper);
        if(umsAdmin != null){
            //查到就缓存下来
            iUmsAdminCacheService.setAdmin(umsAdmin);
        }
        return umsAdmin;
    }

    @Override
    public CommonResult<TokenResult> login(UmsAdminLoginParam umsAdminLoginParam) {
        //用户名查询是否存在
        UmsAdmin umsAdmin = this.getAdminByUsernameAndCache(umsAdminLoginParam.getUsername());
        if(umsAdmin == null){
            Asserts.fail("用户名不存在");
        }
        String salt = umsAdmin.getSalt();
        String password = SecureUtil.md5(umsAdminLoginParam.getPassword()+salt);
        if(!password.equals(umsAdmin.getPassword())){
            Asserts.fail("密码错误");
        }
        log.debug("umsAdmin:{}",JSONUtil.toJsonStr(umsAdmin));
        //生成 token
        String token = jwtTokenUtil.generateToken(new AdminUserDetails(umsAdmin));
        TokenResult tokenResult = new TokenResult();
        tokenResult.setToken(token);
        tokenResult.setTokenHeader(jwtTokenUtil.getTokenHead());
        //将用户信息存入 redis

        return CommonResult.success(tokenResult);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //用户名查询是否存在
        UmsAdmin umsAdmin = this.getAdminByUsernameAndCache(username);
        if(umsAdmin != null){
            //实例化 user对象
            return new AdminUserDetails(umsAdmin);
        }
        throw new UsernameNotFoundException("用户名不存在");
    }


}
