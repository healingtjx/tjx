package com.healing.tjx.common.api;


/**
 * 枚举基本状态码
 */
public enum ResultCode  {
    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 失败
     */
    FAILED(500, "操作失败"),

    /**
     * 参数缺失
     */
    VALIDATE_FAILED(404, "参数检验失败"),

    /**
     * 权限不够
     */
    FORBIDDEN(403, "没有相关权限"),

    /**
     * token 过期
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期");





    private int code;
    private String message;
    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
