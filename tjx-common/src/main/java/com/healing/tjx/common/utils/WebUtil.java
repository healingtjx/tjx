package com.healing.tjx.common.utils;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: tjx
 * @Description: 处理web相关的工具类
 * @Date: 创建于11:44 2020-12-22
 **/
public class WebUtil {


    /**
     * 获取nginx 转发前的ip
     *
     * @param request
     * @return
     */
    public static String getRemoteAddrIp(HttpServletRequest request) {
        String ipFromNginx = getHeader(request, "X-Real-IP");
        return StrUtil.isEmpty(ipFromNginx) ? request.getRemoteAddr()
                : ipFromNginx;
    }

    /**
     * 获取header
     *
     * @param request
     * @param headName
     * @return
     */
    private static String getHeader(HttpServletRequest request, String headName) {
        String value = request.getHeader(headName);
        return (StrUtil.isNotBlank(value) && !"unknown"
                .equalsIgnoreCase(value)) ? value : "";
    }
}
