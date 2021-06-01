package com.healing.tjx.admin.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.healing.tjx.admin.bo.AdminUserDetails;
import com.healing.tjx.admin.dto.AdminInfoResult;
import com.healing.tjx.admin.dto.TokenResult;
import com.healing.tjx.admin.dto.UmsAdminLoginParam;
import com.healing.tjx.admin.service.UmsAdminCacheService;
import com.healing.tjx.admin.service.UmsAuthenticationService;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.ResultCode;
import com.healing.tjx.common.exception.Asserts;
import com.healing.tjx.datasource.entity.UmsAdmin;
import com.healing.tjx.datasource.entity.UmsMenu;
import com.healing.tjx.datasource.entity.UmsRole;
import com.healing.tjx.datasource.mapper.UmsAdminMapper;
import com.healing.tjx.datasource.mapper.UmsMenuMapper;
import com.healing.tjx.datasource.mapper.UmsRoleMapper;
import com.healing.tjx.security.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author tjx
 */
@Slf4j
@Service
public class UmsAuthenticationServiceImpl implements UmsAuthenticationService {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private UmsMenuMapper umsMenuMapper;

    @Resource
    private UmsRoleMapper umsRoleMapper;


    @Autowired
    private UmsAdminCacheService iUmsAdminCacheService;


    @Override
    public UmsAdmin getAdminByUsernameAndCache(String username) {
        //如果缓存有就直接返回缓存
        UmsAdmin admin = iUmsAdminCacheService.getAdmin(username);
        if (admin != null) {
            return admin;
        }
        //如果没有就重新查询
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<UmsAdmin>()
                .eq("username", username);
        UmsAdmin umsAdmin = umsAdminMapper.selectOne(queryWrapper);
        if (umsAdmin != null) {
            //查到就缓存下来
            iUmsAdminCacheService.setAdmin(umsAdmin);
        }
        return umsAdmin;
    }

    @Override
    public CommonResult<TokenResult> login(UmsAdminLoginParam umsAdminLoginParam) {
        //用户名查询是否存在
        UmsAdmin umsAdmin = this.getAdminByUsernameAndCache(umsAdminLoginParam.getUsername());
        if (umsAdmin == null) {
            Asserts.fail("用户名不存在");
        }
        String salt = umsAdmin.getSalt();
        String password = SecureUtil.md5(umsAdminLoginParam.getPassword() + salt);
        if (!password.equals(umsAdmin.getPassword())) {
            Asserts.fail("密码错误");
        }
        log.debug("umsAdmin:{}", JSONUtil.toJsonStr(umsAdmin));
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
        if (umsAdmin != null) {
            //实例化 user对象
            return new AdminUserDetails(umsAdmin);
        }
        throw new UsernameNotFoundException("用户名不存在");
    }

    @Override
    public CommonResult<AdminInfoResult> userInfo(String username) {
        UmsAdmin umsAdmin = this.getAdminByUsernameAndCache(username);
        if (umsAdmin == null) {
            //返回登陆过期
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        //获取 当前用户关联的角色
        //如果没有就重新查询
        List<UmsRole> umsRoles = umsRoleMapper.selectRoleByAdminId(umsAdmin.getId());
        if (umsRoles.size() == 0) {
            Asserts.fail("当前账号没有赋予角色");
        }
        //获取用户授权菜单
        List<Long> ids = umsRoles.stream().map(UmsRole::getId).collect(Collectors.toList());
        List<UmsMenu> umsMenus = umsMenuMapper.selectMenuByRoleIds(ids);
        if (umsMenus.size() == 0) {
            Asserts.fail("当前角色没有菜单权限");
        }
        //获取角色
        List<String> roles = umsRoles.stream().map(UmsRole::getName).collect(Collectors.toList());
        //封装结果
        AdminInfoResult adminInfoResult = new AdminInfoResult();
        adminInfoResult.setUsername(username);
        adminInfoResult.setRoles(roles);
        adminInfoResult.setMenus(umsMenus);
        return CommonResult.success(adminInfoResult);
    }

    @Override
    public CommonResult logout(String username) {
        iUmsAdminCacheService.delAdmin(username);
        return CommonResult.success();
    }


}
