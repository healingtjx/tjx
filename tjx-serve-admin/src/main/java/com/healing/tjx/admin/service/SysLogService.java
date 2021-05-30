package com.healing.tjx.admin.service;

import com.healing.tjx.common.api.PageParam;
import com.healing.tjx.common.api.PageResult;

/**
 * @Author: 作者
 * @Description: 描述
 * @Date: 创建于14:45 2021-05-30
 **/
public interface SysLogService {

    /**
     * 日志列表
     *
     * @param param
     * @param name
     * @return
     */
    PageResult list(PageParam param, String name);
}

