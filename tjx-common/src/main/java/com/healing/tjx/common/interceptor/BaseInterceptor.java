package com.healing.tjx.common.interceptor;

import com.healing.tjx.common.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @Author: tjx
 * @Description: 基础 的拦截器，用于展示请求参数
 * @Date: 创建于16:11 2021-09-09
 **/
@Slf4j
public class BaseInterceptor extends HandlerInterceptorAdapter {




    /**
     * 显示请求参数
     *
     * @param request
     */
    private void showParams(HttpServletRequest request, String targetURL, String ip,BufferedReader bufferedReader)  {
        Map map = new HashMap(8);
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }

        Set<Map.Entry<String, String>> set = map.entrySet();
        log.info("------------------------------");
        log.info("接口地址:" + targetURL);
        log.info("ip地址:" + ip);
        for (Map.Entry entry : set) {
            log.info(entry.getKey() + ":" + entry.getValue());
        }
        Stream<String> lines = bufferedReader.lines();
        lines.forEach(p-> log.info(p));
        log.info("------------------------------");
    }

    /**
     * 用于 参数打印
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {


        BufferedReader bufferedReader = request.getReader();
        //参数打印
        String currentURL = request.getRequestURI();
        String ctxPath = request.getContextPath();
        String targetURL = currentURL.substring(ctxPath.length() + 1);
        String ip = WebUtil.getRemoteAddrIp(request);
        showParams(request, targetURL, ip,bufferedReader);
        return super.preHandle(request,response,o);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }
}
