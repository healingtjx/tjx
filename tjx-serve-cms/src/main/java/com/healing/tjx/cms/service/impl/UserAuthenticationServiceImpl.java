package com.healing.tjx.cms.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.healing.tjx.cms.dto.LoginTypeEnum;
import com.healing.tjx.cms.dto.TokenResult;
import com.healing.tjx.cms.dto.UserBaseDetails;
import com.healing.tjx.cms.dto.WxAuthorizationParam;
import com.healing.tjx.cms.service.UserAuthenticationService;
import com.healing.tjx.cms.service.UserBaseCacheService;
import com.healing.tjx.cms.utils.WxLoginUtil;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.exception.Asserts;
import com.healing.tjx.datasource.entity.UserAuths;
import com.healing.tjx.datasource.entity.UserBase;
import com.healing.tjx.datasource.mapper.UserAuthsMapper;
import com.healing.tjx.datasource.mapper.UserBaseMapper;
import com.healing.tjx.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Author: 作者
 * @Description: 登陆相关
 * @Date: 创建于22:33 2021-06-02
 **/
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${weixin.mini.appid}")
    private String APP_ID;

    @Value("${weixin.mini.secret}")
    private String SECRET;

    @Autowired
    private UserBaseCacheService userBaseCacheService;

    @Resource
    private UserBaseMapper userBaseMapper;

    @Resource
    private UserAuthsMapper userAuthsMapper;

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

    @Override
    public CommonResult appletAuth(WxAuthorizationParam param) {
        String openId = null;
        String phone = null;
        try {

            String sessionKey = userBaseCacheService.getSessionKey(param.getCode());
            if (StrUtil.isEmpty(sessionKey)) {
                //解析code 获得 sessionKey and openId
                JSONObject openIdAndSessionKey = WxLoginUtil.getOpenIdAndSessionKey(param.getCode(), APP_ID, SECRET, restTemplate);
                openId = (String) openIdAndSessionKey.get("openid");
                sessionKey = (String) openIdAndSessionKey.get("session_key");
                //缓存 sessionKey
                userBaseCacheService.setSessionKey(param.getCode(), sessionKey);
            }
            //解析 密钥获取 phone
            JSONObject phoneNumber = WxLoginUtil.getPhoneNumber(param.getEncryptedData(), sessionKey, param.getIv());
            phone = (String) phoneNumber.get("phoneNumber");
        } catch (Exception e) {
            e.printStackTrace();
            Asserts.fail("授权失败");
        }
        //查询用户是否之前登陆过
        LambdaQueryWrapper<UserAuths> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAuths::getIdentityType, LoginTypeEnum.APPLET_PHONE.name())
                .eq(UserAuths::getIdentifier, phone);
        UserAuths userAuths = userAuthsMapper.selectOne(queryWrapper);
        UserBase userBase = null;
        if (userAuths == null) {
            //没有登陆过 创建一个新的用户
            userBase = new UserBase();
            userBase.setNickname(param.getNickName());
            userBase.setAvatar(param.getAvatarUrl());
            userBase.setGender(Integer.parseInt(param.getGender()));
            userBase.setStatus(1);
            userBase.setCreateTime(LocalDateTime.now());
            userBaseMapper.insert(userBase);
            //绑定登陆关系
            userAuths = new UserAuths();
            userAuths.setBindingTime(LocalDateTime.now());
            userAuths.setUserId(userBase.getId());
            userAuths.setIdentityType(LoginTypeEnum.APPLET_PHONE.name());
            userAuths.setIdentifier(phone);
            userAuths.setCredential(openId);
            userAuthsMapper.insert(userAuths);
        } else {
            userBase = userBaseMapper.selectById(userAuths.getUserId());
            if (userBase == null) {
                Asserts.fail("绑定用户不存在");
            }
            //修改登陆时间
            userAuths.setLoginTime(LocalDateTime.now());
            userAuthsMapper.updateById(userAuths);
        }
        //生成登陆信息
        UserDetails userDetails = new UserBaseDetails(userBase);
        String token = jwtTokenUtil.generateToken(userDetails);
        TokenResult tokenResult = new TokenResult();
        tokenResult.setToken(token);
        tokenResult.setTokenHeader(jwtTokenUtil.getTokenHead());
        return CommonResult.success(tokenResult);
    }


}

