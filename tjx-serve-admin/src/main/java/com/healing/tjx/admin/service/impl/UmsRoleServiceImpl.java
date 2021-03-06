package com.healing.tjx.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.healing.tjx.admin.dto.UmsRoleAllocParam;
import com.healing.tjx.admin.dto.UmsRoleChangeParam;
import com.healing.tjx.admin.dto.UpdateStatusParam;
import com.healing.tjx.admin.service.UmsRoleService;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import com.healing.tjx.common.exception.Asserts;
import com.healing.tjx.datasource.entity.UmsRole;
import com.healing.tjx.datasource.entity.UmsRoleMenuRelation;
import com.healing.tjx.datasource.entity.UmsRolePermissionRelation;
import com.healing.tjx.datasource.entity.UmsRoleResourceRelation;
import com.healing.tjx.datasource.mapper.UmsRoleMapper;
import com.healing.tjx.datasource.mapper.UmsRoleMenuRelationMapper;
import com.healing.tjx.datasource.mapper.UmsRolePermissionRelationMapper;
import com.healing.tjx.datasource.mapper.UmsRoleResourceRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: tjx
 * @Description:
 * @Date: 创建于10:27 2020-12-30
 **/
@Slf4j
@Service
public class UmsRoleServiceImpl implements UmsRoleService {


    @Resource
    private UmsRoleMapper umsRoleMapper;

    @Resource
    private UmsRolePermissionRelationMapper umsRolePermissionRelationMapper;

    @Resource
    private UmsRoleResourceRelationMapper umsRoleResourceRelationMapper;

    @Resource
    private UmsRoleMenuRelationMapper umsRoleMenuRelationMapper;

    @Override
    public PageResult<UmsRole> list(PageParam pageParam, String name) {
        //查询条件
        LambdaQueryWrapper<UmsRole> queryWrapper = new LambdaQueryWrapper<>();
        //查询名称
        if (!StrUtil.hasEmpty(name)) {
            queryWrapper.like(UmsRole::getName, name);
        }
        //执行查询
        IPage<UmsRole> selectPage = umsRoleMapper.selectPage(pageParam.generatePagination(), queryWrapper);
        return PageResult.success(selectPage);
    }

    @Override
    public CommonResult<Long> updateUmsRoleStatus(UpdateStatusParam updateStatusParam) {
        //修改
        UmsRole umsRole = new UmsRole();
        umsRole.setId(updateStatusParam.getId().longValue());
        umsRole.setStatus(updateStatusParam.getStatus());
        int i = umsRoleMapper.updateById(umsRole);
        if (i < 1) {
            Asserts.fail("修改失败,请检测传入参数");
        }
        return CommonResult.success(umsRole.getId());

    }

    @Override
    public CommonResult<Long> change(UmsRoleChangeParam umsRoleChangeParam) {
        //封装修改参数
        UmsRole umsRole = new UmsRole();
        umsRole.setName(umsRoleChangeParam.getName());
        if (StrUtil.isNotEmpty(umsRoleChangeParam.getDescription())) {
            umsRole.setDescription(umsRoleChangeParam.getDescription());
        }
        int i;
        //判断是修改还是新增
        if (umsRoleChangeParam.getId() != null) {
            umsRole.setId(umsRoleChangeParam.getId().longValue());
            i = umsRoleMapper.updateById(umsRole);
        } else {
            //初始参数
            umsRole.setSort(0);
            umsRole.setAdminCount(0);
            umsRole.setStatus(1);
            umsRole.setCreateTime(LocalDateTime.now());
            i = umsRoleMapper.insert(umsRole);
        }
        if (i < 1) {
            Asserts.fail("修改失败,请检测传入参数");
        }
        return CommonResult.success(umsRole.getId());
    }

    @Override
    public CommonResult delete(Integer id) {
        //先删除角色关联
        LambdaQueryWrapper<UmsRolePermissionRelation> deleteWrapper = new LambdaQueryWrapper<>();
        //删除 关联 id 所有 关系
        deleteWrapper.eq(UmsRolePermissionRelation::getRoleId, id);
        umsRolePermissionRelationMapper.delete(deleteWrapper);
        //删除校色
        umsRoleMapper.deleteById(id);
        return CommonResult.success(id);
    }

    @Override
    public CommonResult allocMenu(UmsRoleAllocParam allocParam) {
        //删除之前的
        LambdaQueryWrapper<UmsRoleMenuRelation> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(UmsRoleMenuRelation::getRoleId, allocParam.getRoleId());
        umsRoleMenuRelationMapper.delete(queryWrapper);
        //重新添加 (这里就不额外写批量接口，没几个数据)
        Integer[] relationIds = allocParam.getRelationIds();
        for (Integer relationId : relationIds) {
            UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setMenuId(relationId.longValue());
            relation.setRoleId(allocParam.getRoleId().longValue());
            umsRoleMenuRelationMapper.insert(relation);
        }
        return CommonResult.success();
    }

    @Override
    public CommonResult allocResource(UmsRoleAllocParam allocParam) {
        //过滤到 id = -1的
        List<Integer> ids = Arrays.asList(allocParam.getRelationIds())
                .stream()
                .filter(p -> p.intValue() > 0)
                .collect(Collectors.toList());
        //删除之前的
        LambdaQueryWrapper<UmsRoleResourceRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsRoleResourceRelation::getRoleId, allocParam.getRoleId());
        umsRoleResourceRelationMapper.delete(queryWrapper);
        //重新添加 (这里就不额外写批量接口，没几个数据)
        for (Integer relationId : ids) {
            UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setResourceId(relationId.longValue());
            relation.setRoleId(allocParam.getRoleId().longValue());
            umsRoleResourceRelationMapper.insert(relation);
        }
        return CommonResult.success();
    }
}
