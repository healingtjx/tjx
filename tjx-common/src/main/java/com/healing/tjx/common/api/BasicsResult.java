package com.healing.tjx.common.api;


import lombok.Data;

/**
 * @Author: tjx
 * @Description: 全局统一返回类(规范)
 * @Date: 创建于10:33 2020-12-10
 **/
@Data
public class BasicsResult {

    /**
     * 状态码
     */
    private int code;

    /**
     * 提示消息
     */
    private String message;

    BasicsResult(ResultCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    BasicsResult(ResultCode code, String message) {
        this.code = code.getCode();
        this.message = message;
    }


    /**
     * 操作失败返回
     *
     * @return
     */
    public static BasicsResult failed() {
        return new BasicsResult(ResultCode.FAILED);
    }

    /**
     * 操作失败返回 带信息
     *
     * @param message
     * @return
     */
    public static BasicsResult failed(String message) {
        return new BasicsResult(ResultCode.FAILED, message);
    }

    /**
     * 操作失败返回 带信息
     *
     * @return
     */
    public static BasicsResult failed(ResultCode resultCode) {
        return new BasicsResult(resultCode);
    }


    /**
     * 参数验证失败返回结果
     */
    public static BasicsResult validateFailed() {
        return new BasicsResult(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static BasicsResult validateFailed(String message) {
        return new BasicsResult(ResultCode.VALIDATE_FAILED, message);
    }


}
