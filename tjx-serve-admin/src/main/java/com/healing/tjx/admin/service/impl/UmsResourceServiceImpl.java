package com.healing.tjx.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.healing.tjx.admin.dto.AllocResult;
import com.healing.tjx.admin.dto.TreeResult;
import com.healing.tjx.admin.dto.UmsResourceChangeParam;
import com.healing.tjx.admin.dto.UmsResourcePageParam;
import com.healing.tjx.admin.entity.UmsResource;
import com.healing.tjx.admin.entity.UmsResourceCategory;
import com.healing.tjx.admin.entity.UmsRoleResourceRelation;
import com.healing.tjx.admin.mapper.UmsResourceCategoryMapper;
import com.healing.tjx.admin.mapper.UmsResourceMapper;
import com.healing.tjx.admin.mapper.UmsRoleResourceRelationMapper;
import com.healing.tjx.admin.service.UmsResourceService;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageResult;
import com.healing.tjx.common.exception.Asserts;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private UmsRoleResourceRelationMapper umsRoleResourceRelationMapper;

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
    public CommonResult<AllocResult> treeList(int roleId) {
        //查询当前角色对应资源
        LambdaQueryWrapper<UmsRoleResourceRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsRoleResourceRelation::getRoleId, roleId);
        List<UmsRoleResourceRelation> relations = umsRoleResourceRelationMapper.selectList(queryWrapper);

        //查询资源分类
        List<UmsResourceCategory> categoryList = umsResourceCategoryMapper.selectList(null);

        //查询资源
        List<UmsResource> resourceList = umsResourceMapper.selectList(null);
        if (categoryList.size() == 0 || resourceList.size() == 0) {
            return CommonResult.success(new AllocResult());
        }

        //解析资源
        List<TreeResult> treeResults = new ArrayList<>();

        for (UmsResourceCategory category : categoryList) {
            TreeResult father = new TreeResult();
            //资源分类不参与分配(为了防止前端报错，改成负数)
            father.setId(-category.getId().intValue());
            father.setLabel(category.getName());
            //找到匹配等 资源
            List<UmsResource> collect = resourceList.stream()
                    .filter(p -> p.getCategoryId().intValue() == category.getId().intValue())
                    .collect(Collectors.toList());

            //封装成tree
            List<TreeResult> children = new ArrayList<>();
            for (UmsResource sonResource : collect) {
                TreeResult son = new TreeResult();
                son.setId(sonResource.getId().intValue());
                son.setLabel(sonResource.getName());
                children.add(son);
            }
            father.setChildren(children);
            treeResults.add(father);
        }

        // 转化成ids
        List<Long> ids = relations.stream().map(UmsRoleResourceRelation::getResourceId).collect(Collectors.toList());
        //封装结果
        AllocResult allocResult = new AllocResult();
        allocResult.setIds(ids);
        allocResult.setTree(treeResults);
        return CommonResult.success(allocResult);
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
