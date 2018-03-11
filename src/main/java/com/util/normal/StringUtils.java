package com.util.normal;

import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author pgs
 * @version 1.0
 */
public class StringUtils {
    private static final Pattern REG_NUMBERIC = Pattern.compile("[0-9]*");

    /**
     * 字符串非空判断
     *
     * @param target 字符串
     * @return a boolean
     */
    public static boolean isEmpty(String target) {
        return null == target || target.length() == 0 || target.trim().length() == 0;
    }

    /**
     * 字符串非空判断
     *
     * @param target 字符串
     * @return a boolean
     */
    public static boolean isNotEmpty(String target) {
        return !isEmpty(target);
    }

    /**
     * 字符串判断是否为数字
     *
     * @param str 字符串
     */
    public static boolean isNumeric(String str) {
        return REG_NUMBERIC.matcher(str).matches();
    }
}