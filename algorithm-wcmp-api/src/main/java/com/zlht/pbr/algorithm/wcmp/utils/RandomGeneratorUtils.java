package com.zlht.pbr.algorithm.wcmp.utils;

import java.util.Random;

/**
 *
 * @author Administrator
 */
public class RandomGeneratorUtils {

    public static String generateRandomChars(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(chars.length());
            char randomChar = chars.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}