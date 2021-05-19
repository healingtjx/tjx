package com.healing.tjx.admin.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.healing.tjx.admin.config.SysConfigEnum;
import com.healing.tjx.admin.dto.SysConfigDto;
import com.healing.tjx.admin.entity.SysConfig;
import com.healing.tjx.admin.mapper.SysConfigMapper;
import com.healing.tjx.admin.service.SysConfigService;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.exception.Asserts;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 作者
 * @Description: 描述
 * @Date: 创建于21:28 2021-05-18
 **/
@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Resource
    private SysConfigMapper sysConfigMapper;


    @Override
    public CommonResult config() {
        LambdaQueryWrapper<SysConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysConfig::getParamKey, SysConfigEnum.OSS_CONFIG);
        SysConfig config = sysConfigMapper.selectOne(queryWrapper);
        if (config == null) {
            return CommonResult.success(new SysConfigDto());
        }
        //获取内容转化为对象
        String paramValue = config.getParamValue();
        SysConfigDto bean = JSONUtil.toBean(paramValue, SysConfigDto.class);
        return CommonResult.success(bean);
    }

    @Override
    public CommonResult save(SysConfigDto param) {
        //解析参数
        String jsonStr = JSONUtil.toJsonStr(param);
        LambdaQueryWrapper<SysConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysConfig::getParamKey, SysConfigEnum.OSS_CONFIG.toString());
        SysConfig config = sysConfigMapper.selectOne(queryWrapper);
        if (config != null) {
            //如果有就修改
            config.setParamKey(SysConfigEnum.OSS_CONFIG.toString());
            config.setParamValue(jsonStr);
            if (sysConfigMapper.updateById(config) <= 0) {
                Asserts.fail("修改数据库失败");
            }
        } else {
            //没有就新增
            config = new SysConfig();
            config.setParamKey(SysConfigEnum.OSS_CONFIG.toString());
            config.setParamValue(jsonStr);
            config.setStatus(1);
            if (sysConfigMapper.insert(config) <= 0) {
                Asserts.fail("插入数据库失败");
            }
        }
        return CommonResult.success();
    }


}

