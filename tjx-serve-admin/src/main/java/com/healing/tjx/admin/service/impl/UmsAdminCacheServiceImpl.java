package com.healing.tjx.admin.service.impl;

import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.admin.service.IUmsAdminCacheService;
import com.healing.tjx.common.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @作者: tjx
 * @描述:
 * @创建时间: 创建于14:29 2020-12-16
 **/
@Slf4j
@Service
public class UmsAdminCacheServiceImpl implements IUmsAdminCacheService {


    @Value("${redis.database}")
    private static String REDIS_DATABASE;

    @Value("${redis.expire.common}")
    private static Long REDIS_EXPIRE;

    @Value("${redis.keys.admin}")
    private static String REDIS_KEY_ADMIN;


    @Autowired
    private RedisService redisService;


    @Override
    public void setAdmin(UmsAdmin umsAdmin) {
        String key = REDIS_DATABASE + ":" +REDIS_KEY_ADMIN + umsAdmin.getUsername();
        redisService.set(key,umsAdmin,REDIS_EXPIRE);
    }

    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" +REDIS_KEY_ADMIN + username;
        return (UmsAdmin) redisService.get(key);
    }
}
