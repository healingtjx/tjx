package com.healing.tjx.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.healing.tjx.admin.dto.UmsAdminResult;
import com.healing.tjx.admin.entity.UmsAdmin;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author tjx
 * @since 2020-12-11
 */
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    /**
     * 测试
     *
     * @param page
     * @param id
     * @return
     */
    IPage<UmsAdmin> selectPageVo(Page<?> page, @Param("id") Integer id);

    /**
     * 管理员列表(返回结果带角色信息)
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<UmsAdminResult> selectPageAdmin(IPage<UmsAdminResult> page, @Param("ew") Wrapper queryWrapper);
}
