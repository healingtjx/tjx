package com.healing.tjx.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.healing.tjx.admin.dto.UmsResourceCategoryChangeParam;
import com.healing.tjx.admin.entity.UmsResource;
import com.healing.tjx.admin.entity.UmsResourceCategory;
import com.healing.tjx.admin.mapper.UmsResourceCategoryMapper;
import com.healing.tjx.admin.mapper.UmsResourceMapper;
import com.healing.tjx.admin.service.UmsResourceCategoryService;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import com.healing.tjx.common.exception.Asserts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @作者: tjx
 * @描述: 资源分类管理
 * @创建时间: 创建于10:15 2021-01-04
 **/
@Slf4j
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {

    @Resource
    private UmsResourceCategoryMapper umsResourceCategoryMapper;

    @Resource
    private UmsResourceMapper umsResourceMapper;

    @Override
    public PageResult<UmsResourceCategory> list(PageParam page, String name) {
        //查询参数
        LambdaQueryWrapper<UmsResourceCategory> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotEmpty(name)) {
            queryWrapper.like(UmsResourceCategory::getName, name);
        }
        //执行查询
        IPage<UmsResourceCategory> selectPage = umsResourceCategoryMapper.selectPage(page.generatePagination(), queryWrapper);
        return PageResult.success(selectPage);
    }

    @Override
    public CommonResult<Long> change(UmsResourceCategoryChangeParam changeParam) {
        //封装参数
        UmsResourceCategory umsResourceCategory = new UmsResourceCategory();
        umsResourceCategory.setName(changeParam.getName());
        umsResourceCategory.setSort(changeParam.getSort());
        int i;
        if (null != changeParam.getId()) {
            //修改
            umsResourceCategory.setId(changeParam.getId().longValue());
            i = umsResourceCategoryMapper.updateById(umsResourceCategory);
        } else {
            //新增(设置默认参数)
            umsResourceCategory.setCreateTime(LocalDateTime.now());
            i = umsResourceCategoryMapper.insert(umsResourceCategory);
        }
        if (i < 1) {
            Asserts.fail("提交失败,请检测传入参数");
        }
        return CommonResult.success(umsResourceCategory.getId());
    }

    @Override
    public CommonResult<Long> delete(Integer id) {
        //判断是否有 资源引用了改分类
        LambdaQueryWrapper<UmsResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsResource::getCategoryId, id.longValue());
        Integer count = umsResourceMapper.selectCount(queryWrapper);
        if (count > 0) {
            Asserts.fail("请先删除改分类下的资源");
        }
        umsResourceCategoryMapper.deleteById(id);
        return CommonResult.success(id.longValue());
    }
}
