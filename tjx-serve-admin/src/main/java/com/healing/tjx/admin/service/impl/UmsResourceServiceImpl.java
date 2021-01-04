package com.healing.tjx.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.healing.tjx.admin.dto.UmsResourceChangeParam;
import com.healing.tjx.admin.dto.UmsResourcePageParam;
import com.healing.tjx.admin.entity.UmsResource;
import com.healing.tjx.admin.entity.UmsResourceCategory;
import com.healing.tjx.admin.mapper.UmsResourceCategoryMapper;
import com.healing.tjx.admin.mapper.UmsResourceMapper;
import com.healing.tjx.admin.service.UmsResourceService;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageResult;
import com.healing.tjx.common.exception.Asserts;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @作者: tjx
 * @描述: 资源模块
 * @创建时间: 创建于10:19 2021-01-02
 **/
@Service
public class UmsResourceServiceImpl implements UmsResourceService {

    @Resource
    private UmsResourceMapper umsResourceMapper;

    @Resource
    private UmsResourceCategoryMapper umsResourceCategoryMapper;

    @Override
    public PageResult<UmsResource> list(UmsResourcePageParam page) {
        //查询条件
        LambdaQueryWrapper<UmsResource> queryWrapper = new LambdaQueryWrapper();
        //查询名称
        if (!StrUtil.hasEmpty(page.getName())) {
            queryWrapper.like(UmsResource::getName, page.getName());
        }
        //查询URL
        if (!StrUtil.hasEmpty(page.getUrl())) {
            queryWrapper.like(UmsResource::getUrl, page.getUrl());
        }
        //查询 父类id
        if (page.getCategoryId() != null) {
            queryWrapper.eq(UmsResource::getCategoryId, page.getCategoryId().longValue());
        }
        //执行查询
        IPage<UmsResource> selectPage = umsResourceMapper.selectPage(page.generatePagination(), queryWrapper);
        return PageResult.success(selectPage);
    }

    @Override
    public CommonResult<Long> change(UmsResourceChangeParam changeParam) {
        //封装请求对象
        UmsResource umsResource = new UmsResource();
        Integer categoryId = changeParam.getCategoryId();
        if (categoryId != null) {
            //检查CategoryId 是否存在
            LambdaQueryWrapper<UmsResourceCategory> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UmsResourceCategory::getId, categoryId.longValue());
            Integer count = umsResourceCategoryMapper.selectCount(queryWrapper);
            if (count == 0) {
                Asserts.fail("检查CategoryId 不存在");
            }


            umsResource.setCategoryId(categoryId.longValue());
        }
        umsResource.setDescription(changeParam.getDescription());
        umsResource.setUrl(changeParam.getUrl());
        umsResource.setName(changeParam.getName());
        int i;
        //判断是修改还是新增
        if (changeParam.getId() != null) {
            //修改
            umsResource.setId(changeParam.getId().longValue());
            i = umsResourceMapper.updateById(umsResource);
        } else {
            //新增(设置默认参数)
            umsResource.setCreateTime(LocalDateTime.now());
            i = umsResourceMapper.insert(umsResource);
        }
        if (i < 1) {
            Asserts.fail("提交失败,请检测传入参数");
        }
        return CommonResult.success(umsResource.getId());
    }

    @Override
    public CommonResult<Long> delete(Integer id) {
        umsResourceMapper.deleteById(id);
        return CommonResult.success(id.longValue());
    }
}
