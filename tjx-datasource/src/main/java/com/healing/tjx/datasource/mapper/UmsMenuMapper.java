package com.healing.tjx.datasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.healing.tjx.datasource.entity.UmsMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台菜单表 Mapper 接口
 * </p>
 *
 * @author tjx
 * @since 2020-12-11
 */
public interface UmsMenuMapper extends BaseMapper<UmsMenu> {

    /**
     * 根据角色ids 获取菜单
     *
     * @param roleIds 角色id集合
     * @return UmsMenu 菜单
     */
    List<UmsMenu> selectMenuByRoleIds(@Param("roleIds") List<Long> roleIds);
}
