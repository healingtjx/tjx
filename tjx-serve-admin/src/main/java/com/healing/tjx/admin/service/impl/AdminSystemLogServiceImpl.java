package com.healing.tjx.admin.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.healing.tjx.admin.entity.SysLog;
import com.healing.tjx.admin.entity.UmsAdmin;
import com.healing.tjx.admin.mapper.SysLogMapper;
import com.healing.tjx.admin.service.UmsAuthenticationService;
import com.healing.tjx.common.service.SystemLogService;
import com.healing.tjx.common.utils.WebUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @Author: tjx
 * @Description: 继承common模块 到系统日志
 * @Date: 创建于13:56 2021-05-30
 **/
@Service
public class AdminSystemLogServiceImpl implements SystemLogService {

    /**
     * get请求类型
     */
    static final String GET_METHOD = "GET";

    @Resource
    private SysLogMapper sysLogMapper;

    @Autowired
    private UmsAuthenticationService umsAuthenticationService;

    @Override
    public void recordLog(HttpServletRequest request, String methodName, Long time, JoinPoint joinPoint) {
        //判断请求类型
        String method = request.getMethod();
        if (GET_METHOD.equals(method)) {
            return;
        }
        String now = DateUtil.now();
        //1.获取操作用户id
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (StrUtil.isEmpty(userName)) {
            return;
        }
        UmsAdmin admin = umsAuthenticationService.getAdminByUsernameAndCache(userName);
        if (admin == null) {
            return;
        }
        //2.获取参数
        //获取ip
        String currentUrl = request.getRequestURI();
        String ctxPath = request.getContextPath();
        String targetUrl = currentUrl.substring(ctxPath.length() + 1);
        String ip = WebUtil.getRemoteAddrIp(request);
        //封装请求参数
        StringBuffer params = new StringBuffer();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            params.append(paramNames[i] + " : " + JSONUtil.toJsonStr(paramValues[i]));
            params.append(" ");
        }
        //3.生产备注
        String remark = "用户[" + userName + "]" + "在时间[" + now + "],调用[" + targetUrl + "]接口," + "执行" + "[" + methodName + "]方法耗时 " + time + ":ms";
        SysLog sysLog = new SysLog();
        //超过500,就截取出来。更多信息可以通过日志查看
        int length = params.toString().length();
        if (length > 500) {
            params = new StringBuffer(params.substring(0, 500));
        }
        sysLog.setAdminId(admin.getId().intValue());
        sysLog.setParams(params.toString());
        sysLog.setTime(LocalDateTime.now());
        sysLog.setRemark(remark);
        sysLogMapper.insert(sysLog);

    }
}

