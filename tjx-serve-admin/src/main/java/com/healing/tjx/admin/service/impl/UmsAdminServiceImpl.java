package com.healing.tjx.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.admin.mapper.UmsAdminMapper;
import com.healing.tjx.admin.service.UmsAdminService;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import com.healing.tjx.common.api.SortParam;
import com.healing.tjx.common.exception.Asserts;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @作者: tjx
 * @描述: 后台用户管理
 * @创建时间: 创建于10:59 2020-12-24
 **/
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    /**
     * 定义 排序字段:格式(方法_SORT_KEYS)
     */
    private static final String[] LIST_SORT_KEYS = {"id", "create_time"};

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Override
    public PageResult<UmsAdmin> list(PageParam pageParam, SortParam sort, String name) {
        //查询
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "username", "nick_name", "create_time", "login_time", "status");

        //查询名称
        if (!StrUtil.hasEmpty(name)) {
            queryWrapper.like("concat(username,nick_name)", name);
        }
        //排序
        if (sort != null) {
            //判断是否属于排序字段
            if (!ArrayUtil.contains(LIST_SORT_KEYS, sort.getKey())) {
                Asserts.fail("排序字段必须存在于:" + JSONUtil.toJsonStr(LIST_SORT_KEYS));
            }
            //是否降序
            if (sort.getIsDescen() != null && sort.getIsDescen()) {
                queryWrapper.orderByDesc(sort.getKey());
            } else {
                queryWrapper.orderByAsc(sort.getKey());
            }
        }
        //执行查询
        IPage<UmsAdmin> umsAdminPage = umsAdminMapper.selectPage(pageParam.generatePagination(), queryWrapper);
        return PageResult.success(umsAdminPage);
    }
}
