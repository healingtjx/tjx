package com.healing.tjx.common.api;


import lombok.Getter;
import lombok.Setter;

/**
 * @作者: tjx
 * @描述: 基础返回类
 * @创建时间: 创建于10:36 2020-12-10
 **/
@Getter
@Setter
public class CommonResult<T> extends BasicsResult {

    /**
     * 返回结果
     */
    private T data;

    CommonResult(ResultCode code) {
        super(code);
    }

    CommonResult(ResultCode code,T data) {
        super(code);
        this.data = data;
    }
    CommonResult(ResultCode code,String message,T data) {
        super(code,message);
        this.data = data;
    }


    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(ResultCode.SUCCESS);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS,data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS, message, data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN, ResultCode.FORBIDDEN.getMessage(), data);
    }


}
