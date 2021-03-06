package com.healing.tjx.common.exception;

import com.healing.tjx.common.api.ResultCode;

/**
 * @Author: tjx
 * @Description: 自定义 接口 异常
 * @Date: 创建于14:43 2020-12-14
 **/
public class ApiException extends RuntimeException {

    private ResultCode resultCode;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
