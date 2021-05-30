package com.healing.tjx.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.healing.tjx.admin.dto.SysLogResult;
import com.healing.tjx.admin.mapper.SysLogMapper;
import com.healing.tjx.admin.service.SysLogService;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: 作者
 * @Description: 描述
 * @Date: 创建于14:45 2021-05-30
 **/
@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;


    @Override
    public PageResult list(PageParam param, String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StrUtil.isNotEmpty(name)) {
            queryWrapper.like("l.remark", name);
        }
        queryWrapper.orderByDesc("l.time");
        IPage<SysLogResult> iPage = sysLogMapper.selectLogPage(param.generatePagination(), queryWrapper);
        return PageResult.success(iPage);
    }
}

