package com.wdg.system.utils;

import java.security.SecureRandom;

/**
 * 盐值处理
 */
public class saltUtil {

    /**
     * 生成盐值
     *
     * @return
     */
    public static String getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[6];
        secureRandom.nextBytes(randomBytes);

        StringBuilder hex = new StringBuilder();
        for (byte b : randomBytes) {
            hex.append(String.format("%02X", b));
        }
        return hex.toString();
    }

    public static void main(String[] args) {
        System.out.println(getSalt());
    }
}
