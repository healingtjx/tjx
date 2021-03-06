package com.healing.tjx.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.healing.tjx.admin.dto.UmsAdminAssignParam;
import com.healing.tjx.admin.dto.UmsAdminChangeParam;
import com.healing.tjx.admin.dto.UpdateStatusParam;
import com.healing.tjx.admin.service.UmsAdminService;
import com.healing.tjx.admin.utils.SaltUtil;
import com.healing.tjx.common.api.CommonResult;
import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;
import com.healing.tjx.common.api.SortParam;
import com.healing.tjx.common.exception.Asserts;
import com.healing.tjx.datasource.entity.UmsAdmin;
import com.healing.tjx.datasource.entity.UmsRole;
import com.healing.tjx.datasource.entity.UmsRolePermissionRelation;
import com.healing.tjx.datasource.expand.UmsAdminResult;
import com.healing.tjx.datasource.mapper.UmsAdminMapper;
import com.healing.tjx.datasource.mapper.UmsRoleMapper;
import com.healing.tjx.datasource.mapper.UmsRolePermissionRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: tjx
 * @Description: 后台用户管理
 * @Date: 创建于10:59 2020-12-24
 **/
@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    /**
     * 定义 排序字段:格式(方法_SORT_KEYS)
     */
    private static final String[] LIST_SORT_KEYS = {"id", "create_time"};

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private UmsRoleMapper umsRoleMapper;

    @Resource
    private UmsRolePermissionRelationMapper umsRolePermissionRelationMapper;

    @Override
    public PageResult<UmsAdminResult> list(PageParam pageParam, SortParam sort, String name) {
        //查询
        QueryWrapper queryWrapper = new QueryWrapper<>();

        //查询名称
        if (!StrUtil.hasEmpty(name)) {
            queryWrapper.like("concat(s.username,s.nick_name)", name);
        }

        //排序
        if (StrUtil.isNotEmpty(sort.getSortKey())) {
            //判断是否属于排序字段
            if (!ArrayUtil.contains(LIST_SORT_KEYS, sort.getSortKey())) {
                Asserts.fail("排序字段必须存在于:" + JSONUtil.toJsonStr(LIST_SORT_KEYS));
            }
            //是否降序
            if (sort.getSortDescen() != null && sort.getSortDescen()) {
                queryWrapper.orderByDesc("s." + sort.getSortKey());
            } else {
                queryWrapper.orderByAsc("s." + sort.getSortKey());
            }
        }
        //执行查询
        IPage<UmsAdminResult> umsAdminPage = umsAdminMapper.selectPageAdmin(pageParam.generatePagination(), queryWrapper);

        return PageResult.success(umsAdminPage);
    }

    @Override
    public PageResult<UmsRole> accreditList(int adminId) {
        List<UmsRole> roles = umsRoleMapper.selectRoleByAdminId(adminId);
        return PageResult.success(roles);
    }

    @Override
    public CommonResult assign(UmsAdminAssignParam param) {
        //判断管理员
        long adminId = param.getAdminId();
        UmsAdmin admin = umsAdminMapper.selectById(adminId);
        if (admin == null) {
            Asserts.fail("用户不存在");
        }
        //清空之前配置
        LambdaQueryWrapper<UmsRolePermissionRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsRolePermissionRelation::getPermissionId, adminId);
        umsRolePermissionRelationMapper.delete(queryWrapper);
        //重写配置新的
        Long[] roleIds = param.getRoleIds();
        for (Long roleId : roleIds) {
            UmsRolePermissionRelation relation = new UmsRolePermissionRelation();
            relation.setPermissionId(adminId);
            relation.setRoleId(roleId);
            umsRolePermissionRelationMapper.insert(relation);
        }
        return CommonResult.success();
    }

    @Override
    public CommonResult<Long> change(UmsAdminChangeParam changeParam) {
        //封装修改参数
        UmsAdmin umsAdmin = new UmsAdmin();
        //需要手动校验的参数
        if (StrUtil.isNotEmpty(changeParam.getPassword())) {
            //生成密码
            String salt = SaltUtil.getNextSalt();
            String password = SecureUtil.md5(changeParam.getPassword() + salt);
            umsAdmin.setPassword(password);
            umsAdmin.setSalt(salt);
        }
        //校验过的参数
        umsAdmin.setUsername(changeParam.getUsername());
        umsAdmin.setNickName(changeParam.getNickName());
        umsAdmin.setEmail(changeParam.getEmail());
        umsAdmin.setNote(changeParam.getNote());
        //判断 账号是否唯一
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", umsAdmin.getUsername());
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectList(queryWrapper);
        int size = umsAdmins.size();
        if (size > 0) {
            UmsAdmin queryUmsAdmin = umsAdmins.get(0);
            log.debug("queryUmsAdmin:{},umsAdminId:{}", queryUmsAdmin, changeParam.getId());
            if (queryUmsAdmin != null && (queryUmsAdmin.getId().intValue() != changeParam.getId())) {
                Asserts.fail("账户名已经存在");
            }
        }
        int i;
        //判断是修改还是新增
        if (changeParam.getId() != null) {
            // 判断下
            //修改
            umsAdmin.setId(changeParam.getId().longValue());
            i = umsAdminMapper.updateById(umsAdmin);
        } else {
            //默认参数
            umsAdmin.setStatus(1);
            umsAdmin.setCreateTime(LocalDateTime.now());
            //新增
            i = umsAdminMapper.insert(umsAdmin);
        }
        if (i <= 0) {
            Asserts.fail("提交失败");
        }
        return CommonResult.success(umsAdmin.getId());
    }

    @Override
    public CommonResult<Integer> delete(int id) {

        int i = umsAdminMapper.deleteById(id);
        if (i <= 0) {
            Asserts.fail("删除失败");
        }
        return CommonResult.success(id);
    }

    @Override
    public CommonResult<Long> updateUmsAdminStatus(UpdateStatusParam statusParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(statusParam.getId().longValue());
        umsAdmin.setStatus(statusParam.getStatus());
        int i = umsAdminMapper.updateById(umsAdmin);
        if (i < 1) {
            Asserts.fail("修改失败,请检测传入参数");
        }
        return CommonResult.success(umsAdmin.getId());
    }
}
