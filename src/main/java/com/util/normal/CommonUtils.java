package com.util.normal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author genshan pan
 * @version 1.0
 * @Date Jul 3, 2017
 * @Time 10:48:39 AM
 */
public class CommonUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean isEmpty(Object obj) {
        if (obj instanceof String) {
            return ((String) obj).trim().length() == 0;
        }
        return null == obj;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 时间格式化
     *
     * @param date   时间
     * @param format 格式
     */
    public static String timeFormat(Date date, String format) {
        if (isNotEmpty(format) && isNotEmpty(date)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        }
        return null;
    }

    /**
     * 获取现在的时间 精确到毫秒，一般用来对文件重命名
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }

    public static Integer convertStringToInteger(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        String[] strs = str.split("\\.");
        return Integer.parseInt(strs[0]);
    }

    /**
     * 字符串转化为浮点型
     *
     * @param str 字符串
     * @return double
     */
    public static Float convertStringToDouble(String str) {
        if (isEmpty(str)) {
            return 0.00f;
        }
        try {
            return Float.valueOf(str);
        } catch (Exception e) {
            return 0.00f;
        }
    }
}