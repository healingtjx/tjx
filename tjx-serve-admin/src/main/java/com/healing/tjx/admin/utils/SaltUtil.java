package com.healing.tjx.admin.utils;

import sun.misc.BASE64Encoder;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 用于 salt 生成
 * @time 2020-12-14.
 * @author tjx
 */
public class SaltUtil {

    /**
     * 生成盐
     * @return String
     */
    public static String getNextSalt()   {
        Random RANDOM = new SecureRandom();
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return new BASE64Encoder().encode(salt);
    }

}
