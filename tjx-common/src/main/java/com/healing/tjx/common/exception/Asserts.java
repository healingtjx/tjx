package com.healing.tjx.common.exception;

import com.healing.tjx.common.api.ResultCode;

/**
 * @作者: tjx
 * @描述: 断言处理类，用于抛出service异常
 * @创建时间: 创建于14:45 2020-12-14
 **/
public class Asserts {

    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(ResultCode errorCode) {
        throw new ApiException(errorCode);
    }
}
