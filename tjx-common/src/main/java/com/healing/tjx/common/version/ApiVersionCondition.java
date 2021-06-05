package com.healing.tjx.common.version;

import cn.hutool.core.util.StrUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: tjx
 * @Description: 版本控制
 * @Date: 创建于 04:06 2021-06-05
 **/
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    Logger logger = Logger.getLogger(ApiVersionCondition.class);

    // 路径中版本的前缀， 这里用 /v[1-9]/的形式
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");

    private int apiVersion;

    private String[] notNull;

    public ApiVersionCondition(int apiVersion, String[] notNull) {
        this.apiVersion = apiVersion;
        this.notNull = notNull;
    }

    /**
     * 将不同的筛选条件合并,这里采用的覆盖，即后来的规则生效
     *
     * @param other
     * @return
     */
    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        return new ApiVersionCondition(other.getApiVersion(), other.getNotNull());
    }

    /**
     * 根据request查找匹配到的筛选条件
     *
     * @param request
     * @return
     */
    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
        if (m.find()) {
            Integer version = Integer.valueOf(m.group(1));
            if (version >= this.apiVersion) {
                //判断是否又notNull注解
                if (notNull != null) {
                    int length = notNull.length;
                    //遍历
                    for (int i = 0; i < length; i++) {
                        String par = notNull[i];
                        String parameter = request.getParameter(par);
                        //判断不能为空参数
                        if (StrUtil.isEmpty(parameter)) {
                            //日志输出必传入参数
                            logger.error("参数" + par + "不能为空");
                            return null;
                        }
                    }
                }
                // 如果请求的版本号大于配置版本号， 则满足，即与请求的
                return this;
            }
        }
        return null;
    }

    //实现不同条件类的比较，从而实现优先级排序
    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        return other.getApiVersion() - this.apiVersion;
    }

    public int getApiVersion() {
        return apiVersion;
    }

    public String[] getNotNull() {
        return notNull;
    }

}

