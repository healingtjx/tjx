package com.healing.tjx.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.healing.tjx.admin.dto.UmsMenuChangeParam;
import com.healing.tjx.admin.dto.UpdateStatusParam;
import com.healing.tjx.admin.entity.UmsMenu;
import com.healing.tjx.admin.mapper.UmsMenuMapper;
import com.healing.tjx.admin.service.UmsMenuService;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import com.healing.tjx.common.exception.Asserts;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @作者: tjx
 * @描述: 菜单管理
 * @创建时间: 创建于10:22 2020-12-31
 **/
@Service
public class UmsMenuServiceImpl implements UmsMenuService {

    @Resource
    private UmsMenuMapper umsMenuMapper;


    @Override
    public PageResult<UmsMenu> list(PageParam page, int pid) {
        //查询条件
        LambdaQueryWrapper<UmsMenu> queryWrapper = new LambdaQueryWrapper<>();
        //根据pid 查询
        queryWrapper.eq(UmsMenu::getParentId, pid);
        //执行查询
        IPage<UmsMenu> selectPage = umsMenuMapper.selectPage(page.generatePagination(), queryWrapper);
        return PageResult.success(selectPage);
    }

    @Override
    public CommonResult<Long> change(UmsMenuChangeParam umsMenuChangeParam) {
        //字段赋值
        Integer parentId = umsMenuChangeParam.getParentId();
        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setName(umsMenuChangeParam.getName());
        umsMenu.setParentId(parentId.longValue());
        umsMenu.setTitle(umsMenuChangeParam.getTitle());
        umsMenu.setIcon(umsMenuChangeParam.getIcon());
        umsMenu.setSort(umsMenuChangeParam.getSort());
        //计算level
        umsMenu.setLevel(parentId.intValue() == 0 ? 0 : 1);
        int i;
        //判断是新增还是修改
        if (umsMenuChangeParam.getId() != null) {
            //判断是否有下级菜单
            UmsMenu oldMmsMenu = umsMenuMapper.selectById(umsMenuChangeParam.getId());
            if(oldMmsMenu == null){
                Asserts.fail("找不到您要修改的菜单");
            }
            //如果需要修改pid
            if(oldMmsMenu.getParentId().intValue() != umsMenu.getParentId()){
                //判断是否还有下级菜单
                //查询条件
                LambdaQueryWrapper<UmsMenu> queryWrapper = new LambdaQueryWrapper<>();
                //根据pid 查询
                queryWrapper.eq(UmsMenu::getParentId, umsMenuChangeParam.getId());
                Integer count = umsMenuMapper.selectCount(queryWrapper);
                if (count > 0) {
                    Asserts.fail("请先删除当前菜单下的二级菜单");
                }
            }
            //修改
            umsMenu.setId(umsMenuChangeParam.getId().longValue());
            i = umsMenuMapper.updateById(umsMenu);
        } else {
            //新增 (设置默认值)
            umsMenu.setHidden(0);
            umsMenu.setCreateTime(LocalDateTime.now());
            i = umsMenuMapper.insert(umsMenu);
        }
        if (i <= 0) {
            Asserts.fail("提交失败");
        }
        return CommonResult.success(umsMenu.getId());
    }

    @Override
    public CommonResult<Long> updateUmsMenuHidden(UpdateStatusParam updateStatusParam) {
        //封装参数
        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setId(updateStatusParam.getId().longValue());
        umsMenu.setHidden(updateStatusParam.getStatus());
        int i = umsMenuMapper.updateById(umsMenu);
        if (i < 1) {
            Asserts.fail("修改失败,请检测传入参数");
        }
        return CommonResult.success(umsMenu.getId());
    }

    @Override
    public CommonResult delete(int id) {
        //判断是否还有下级菜单
        //查询条件
        LambdaQueryWrapper<UmsMenu> queryWrapper = new LambdaQueryWrapper<>();
        //根据pid 查询
        queryWrapper.eq(UmsMenu::getParentId, id);
        Integer count = umsMenuMapper.selectCount(queryWrapper);
        if (count > 0) {
            Asserts.fail("请先删除当前菜单下的二级菜单");
        }
        //删除
        umsMenuMapper.deleteById(id);
        return CommonResult.success();
    }
}
