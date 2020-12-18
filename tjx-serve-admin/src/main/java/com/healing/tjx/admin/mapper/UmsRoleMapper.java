package com.healing.tjx.admin.mapper;

import com.healing.tjx.admin.entity.UmsRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author tjx
 * @since 2020-12-11
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    /**
     * 根据 adminId 查询所绑定的校色
     * @param adminId
     * @return List<UmsRole> 角色列表
     */
    List<UmsRole> selectRoleByAdminId(@Param("adminId") long adminId);
}
