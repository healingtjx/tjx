package com.healing.tjx.datasource.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.healing.tjx.datasource.entity.SysLog;
import com.healing.tjx.datasource.expand.SysLogResult;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tjx
 * @since 2021-05-30
 */
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 根据名称分页查询
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<SysLogResult> selectLogPage(IPage<?> page, @Param("ew") Wrapper<SysLog> queryWrapper);
}
