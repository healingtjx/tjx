package com.healing.tjx.cms.service.impl;

import com.healing.tjx.cms.dto.UserBaseDetails;
import com.healing.tjx.cms.service.UserAuthenticationService;
import com.healing.tjx.cms.service.UserBaseCacheService;
import com.healing.tjx.datasource.entity.UserBase;
import com.healing.tjx.datasource.mapper.UserBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 作者
 * @Description: 登陆相关
 * @Date: 创建于22:33 2021-06-02
 **/
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private UserBaseCacheService userBaseCacheService;

    @Resource
    private UserBaseMapper userBaseMapper;

    @Override
    public UserBase getAdminByUserIdAndCache(String userId) {
        //如果缓存有就直接返回缓存
        UserBase user = userBaseCacheService.getUser(userId);
        if (user != null) {
            return user;
        }
        //如果没有就重新查询
        user = userBaseMapper.selectById(Integer.parseInt(userId));
        if (user != null) {
            userBaseCacheService.setUser(user);
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUserId(String userId) {
        UserBase userBase = this.getAdminByUserIdAndCache(userId);
        if (userBase != null) {
            return new UserBaseDetails(userBase);
        }
        throw new UsernameNotFoundException("用户名不存在");
    }


}

