package com.healing.tjx.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.healing.tjx.admin.dto.AllocResult;
import com.healing.tjx.admin.dto.TreeResult;
import com.healing.tjx.admin.dto.UmsMenuChangeParam;
import com.healing.tjx.admin.dto.UpdateStatusParam;
import com.healing.tjx.admin.entity.UmsMenu;
import com.healing.tjx.admin.entity.UmsRoleMenuRelation;
import com.healing.tjx.admin.mapper.UmsMenuMapper;
import com.healing.tjx.admin.mapper.UmsRoleMenuRelationMapper;
import com.healing.tjx.admin.service.UmsMenuService;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import com.healing.tjx.common.exception.Asserts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @作者: tjx
 * @描述: 菜单管理
 * @创建时间: 创建于10:22 2020-12-31
 **/
@Slf4j
@Service
public class UmsMenuServiceImpl implements UmsMenuService {

    @Resource
    private UmsMenuMapper umsMenuMapper;

    @Resource
    private UmsRoleMenuRelationMapper umsRoleMenuRelationMapper;


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
    public CommonResult<AllocResult> treeList(int roleId) {
        //查询当前角色对应的权限
        LambdaQueryWrapper<UmsRoleMenuRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsRoleMenuRelation::getRoleId, roleId);
        List<UmsRoleMenuRelation> relations = umsRoleMenuRelationMapper.selectList(queryWrapper);

        //查询菜单
        List<UmsMenu> umsMenus = umsMenuMapper.selectList(null);
        if (umsMenus.size() == 0) {
            return CommonResult.success(new AllocResult());
        }
        //解析菜单
        List<TreeResult> treeResults = new ArrayList<>();
        List<UmsMenu> fatherMenuList = umsMenus.stream()
                .filter(p -> p.getParentId().intValue() == 0)
                .collect(Collectors.toList());
        for (UmsMenu menu : fatherMenuList) {
            TreeResult father = new TreeResult();
            father.setId(menu.getId().intValue());
            father.setLabel(menu.getTitle());
            //解析子菜单
            List<UmsMenu> sonMenuList = umsMenus.stream()
                    .filter(p -> p.getParentId().intValue() == menu.getId())
                    .collect(Collectors.toList());
            //封装成tree
            List<TreeResult> children = new ArrayList<>();
            for (UmsMenu sonMenu : sonMenuList) {
                TreeResult son = new TreeResult();
                son.setId(sonMenu.getId().intValue());
                son.setLabel(sonMenu.getTitle());
                children.add(son);
            }
            father.setChildren(children);
            treeResults.add(father);
        }
        //转化成ids (并且排除 父类菜单id)
        List<Long> ids = new ArrayList<>();
        for (UmsRoleMenuRelation relation : relations) {
            //排除 父类菜单
            UmsMenu menu = fatherMenuList.stream()
                    .filter(b -> b.getId().intValue() == relation.getMenuId().intValue())
                    .findFirst()
                    .orElse(null);
            if (menu != null && menu.getParentId().intValue() == 0) {
                continue;
            }
            ids.add(relation.getMenuId());
        }

        //封装结果
        AllocResult allocResult = new AllocResult();
        allocResult.setIds(ids);
        allocResult.setTree(treeResults);
        return CommonResult.success(allocResult);
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
            if (oldMmsMenu == null) {
                Asserts.fail("找不到您要修改的菜单");
            }
            //如果需要修改pid
            if (oldMmsMenu.getParentId().intValue() != umsMenu.getParentId()) {
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
