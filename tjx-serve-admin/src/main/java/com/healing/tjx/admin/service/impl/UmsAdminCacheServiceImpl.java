package com.healing.tjx.admin.service.impl;

import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.admin.service.UmsAdminCacheService;
import com.healing.tjx.common.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于14:29 2020-12-16
 **/
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
@Slf4j
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {


    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;

    @Value("${redis.keys.admin}")
    private String REDIS_KEY_ADMIN;


    @Autowired
    private RedisService redisService;


    @Override
    public void setAdmin(UmsAdmin umsAdmin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + umsAdmin.getUsername();
        redisService.set(key, umsAdmin, REDIS_EXPIRE);
    }

    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + username;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void delAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + username;
        redisService.del(key);
    }
}
