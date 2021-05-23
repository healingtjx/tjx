package com.healing.tjx.admin.service;

import com.healing.tjx.admin.dto.SysConfigDto;
import com.healing.tjx.common.api.CommonResult;

/**
 * @Author: 作者
 * @Description: 描述
 * @Date: 创建于21:28 2021-05-18
 **/
public interface SysConfigService {


    /**
     * 获取OSS配置信息
     *
     * @return
     */
    CommonResult configOSS();

    /**
     * 保存OSS配置
     *
     * @return
     */
    CommonResult saveOSS(SysConfigDto param);
}

