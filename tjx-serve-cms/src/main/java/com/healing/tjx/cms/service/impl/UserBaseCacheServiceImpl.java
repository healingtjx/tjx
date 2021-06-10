package com.healing.tjx.cms.service.impl;

import com.healing.tjx.cms.service.UserBaseCacheService;
import com.healing.tjx.common.service.RedisService;
import com.healing.tjx.datasource.entity.UserBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: 作者
 * @Description: 描述
 * @Date: 创建于22:44 2021-06-02
 **/
@Service
public class UserBaseCacheServiceImpl implements UserBaseCacheService {


    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;

    @Value("${redis.keys.admin}")
    private String REDIS_KEY_ADMIN;

    @Value("${redis.keys.sessionKey}")
    private String REDIS_KEY_SESSION_KEY;

    @Autowired
    private RedisService redisService;

    @Override
    public void setUser(UserBase userBase) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + userBase.getId();
        redisService.set(key, userBase, REDIS_EXPIRE);
    }

    @Override
    public UserBase getUser(String userId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + userId;
        return (UserBase) redisService.get(key);
    }

    @Override
    public void delUser(String userId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + userId;
        redisService.del(key);
    }

    @Override
    public String getSessionKey(String code) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_SESSION_KEY + code;
        return (String) redisService.get(key);
    }

    @Override
    public void setSessionKey(String code, String sessionKey) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_SESSION_KEY + code;
        redisService.set(key, sessionKey, REDIS_EXPIRE);
    }
}

