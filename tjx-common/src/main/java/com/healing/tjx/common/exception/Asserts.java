package com.healing.tjx.common.exception;

import com.healing.tjx.common.api.ResultCode;

/**
 * @Author: tjx
 * @Description: 断言处理类，用于抛出service异常
 * @Date: 创建于14:45 2020-12-14
 **/
public class Asserts {

    /**
     * 业务层错误返回
     *
     * @param message
     */
    public static void fail(String message) {
        throw new ApiException(message);
    }

    /**
     * 返回指定错误
     *
     * @param errorCode
     */
    public static void fail(ResultCode errorCode) {
        throw new ApiException(errorCode);
    }
}
