package com.healing.tjx.cms.dto;

/**
 * @author tjx
 * @Description 登陆类型
 * @Date 2021/6/10 10:53 下午
 */
public enum LoginTypeEnum {
    /**
     * 微信小程序-手机号码
     */
    APPLET_PHONE("APPLET_PHONE");

    private String key;

    LoginTypeEnum(String key) {
        this.key = key;
    }

}
