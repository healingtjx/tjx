package com.healing.tjx.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.admin.mapper.UmsAdminMapper;
import com.healing.tjx.admin.service.UmsAdminService;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageResult;
import com.healing.tjx.common.api.ResultCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @作者: tjx
 * @描述:
 * @创建时间: 创建于10:59 2020-12-24
 **/
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Override
    public PageResult<UmsAdmin> list() {
        Wrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectList(queryWrapper);
        return PageResult.success(umsAdmins);
    }
}
