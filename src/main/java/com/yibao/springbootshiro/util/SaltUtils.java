package com.yibao.springbootshiro.util;

import java.util.Random;

/**
 * 工具类
 *
 * @author liyi
 * @create 2021 -10 -18 -14:34
 */
public class SaltUtils { // 工具类：用于生成随机盐

    /**
     * 根据传入的长度，随机生成 "盐"
     *
     * @param num 需要的盐长度
     * @return
     */
    public static String getSalt(int num) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@$%^&*";
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toCharArray();
        for (int i = 0; i < num; i++) {
            char c = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

}
