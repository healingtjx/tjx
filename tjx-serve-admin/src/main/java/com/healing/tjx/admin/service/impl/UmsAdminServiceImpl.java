package com.healing.tjx.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.admin.mapper.UmsAdminMapper;
import com.healing.tjx.admin.service.UmsAdminService;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @作者: tjx
 * @描述: 后台用户管理
 * @创建时间: 创建于10:59 2020-12-24
 **/
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Override
    public PageResult<UmsAdmin> list(PageParam pageParam, String name) {
        //查询
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "username", "nick_name", "create_time", "login_time", "status");
        if (!StrUtil.hasEmpty(name)) {
            queryWrapper.like("concat(username,nick_name)", name);
        }
        //执行查询
        IPage<UmsAdmin> umsAdminPage = umsAdminMapper.selectPage(pageParam.generatePagination(), queryWrapper);
        return PageResult.success(umsAdminPage);
    }
}
