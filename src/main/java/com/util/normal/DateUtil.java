package com.util.normal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 *
 * @author pgs
 * @version 1.0
 */
public final class DateUtil {

    private static final Map<String, ThreadLocal<SimpleDateFormat>> timestampFormatPool = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    private static final Map<String, ThreadLocal<SimpleDateFormat>> dateFormatPool = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    private static final Object timestampFormatLock = new Object();

    private static final Object dateFormatLock = new Object();

    private static final String dateFormatPattern = "yyyy-MM-dd";

    private static final String dateFormatPattern_CN = "yyyy年MM月dd日";

    private static final String timestampPattern = "yyyy-MM-dd HH:mm:ss";

    private static final String simplifyDatePattern = "yyyyMMdd";

    private static final String simplifyTimestampPattern = "yyyyMMddHHmmss";

    private static final String timestampPatternCps = "yyyyMMddHHmmss";

    private static SimpleDateFormat getDateFormatCN() {
        ThreadLocal<SimpleDateFormat> tl = dateFormatPool.get(dateFormatPattern_CN);
        tl = getDateFormatThreadLocal(tl, dateFormatLock, dateFormatPool, dateFormatPattern_CN);
        return tl.get();
    }

    private static ThreadLocal<SimpleDateFormat> getDateFormatThreadLocal(ThreadLocal<SimpleDateFormat> tl, Object dateFormatLock, Map<String, ThreadLocal<SimpleDateFormat>> dateFormatPool, final String dateFormatPattern_cn) {
        if (null == tl) {
            synchronized (dateFormatLock) {
                tl = dateFormatPool.get(dateFormatPattern_cn);
                if (null == tl) {
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        protected synchronized SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(dateFormatPattern_cn);
                        }
                    };
                    dateFormatPool.put(dateFormatPattern_cn, tl);
                }
            }
        }
        return tl;
    }

    private static SimpleDateFormat getDateFormat() {
        ThreadLocal<SimpleDateFormat> tl = dateFormatPool.get(dateFormatPattern);
        tl = getDateFormatThreadLocal(tl, dateFormatLock, dateFormatPool, dateFormatPattern);
        return tl.get();
    }

    private static SimpleDateFormat getTimestampFormat() {
        ThreadLocal<SimpleDateFormat> tl = timestampFormatPool.get(timestampPattern);
        tl = getDateFormatThreadLocal(tl, timestampFormatLock, timestampFormatPool, timestampPattern);
        return tl.get();
    }

    private static SimpleDateFormat getSimplifyTimestampFormat() {
        ThreadLocal<SimpleDateFormat> tl = timestampFormatPool.get(simplifyTimestampPattern);
        tl = getDateFormatThreadLocal(tl, timestampFormatLock, timestampFormatPool, simplifyTimestampPattern);
        return tl.get();
    }

    private static SimpleDateFormat getSimplifyDateFormat() {
        ThreadLocal<SimpleDateFormat> tl = timestampFormatPool.get(simplifyDatePattern);
        tl = getDateFormatThreadLocal(tl, timestampFormatLock, timestampFormatPool, simplifyDatePattern);
        return tl.get();
    }

    private static SimpleDateFormat getTimestampFormatCps() {
        ThreadLocal<SimpleDateFormat> tl = timestampFormatPool.get(timestampPatternCps);
        tl = getDateFormatThreadLocal(tl, timestampFormatLock, timestampFormatPool, timestampPatternCps);
        return tl.get();
    }

    /**
     * 格式化成时间戳格式
     *
     * @param date 要格式化的日期对象
     * @return "年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串
     */
    public static String timestampFormat(Date date) {
        if (date == null) {
            return "";
        }
        return getTimestampFormat().format(date);
    }

    /**
     * 格式化成时间戳格式
     *
     * @param date 要格式化的日期对象
     * @return "年年年年月月日日时时分分秒秒"格式的日期字符串
     */
    public static String simplifyTimestampFormat(Date date) {
        if (date == null) {
            return "";
        }
        return getSimplifyTimestampFormat().format(date);
    }

    /**
     * 格式化成日期格式
     *
     * @param date 要格式化的日期对象
     * @return "年年年年月月日日"格式的日期字符串
     */
    public static String simplifyDateFormat(Date date) {
        if (date == null) {
            return "";
        }
        return getSimplifyDateFormat().format(date);
    }

    /**
     * 格式化成时间戳格式
     *
     * @param date 要格式化的日期对象
     * @return "年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串
     */
    public static String timestampFormatCps(Date date) {
        if (date == null) {
            return "";
        }
        return getTimestampFormatCps().format(date);
    }

    /**
     * 格式化成Unix时间戳格式
     *
     * @param date 时间
     * @return unix时间戳
     */
    public static long unixTimestampFormat(Date date) {
        String unixDate = String.valueOf(date.getTime()).substring(0, 10);
        return Long.parseLong(unixDate);
    }

    /**
     * 格式化成时间戳格式
     *
     * @param datetime 要格式化的日期
     * @return "年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串
     */
    public static String timestampFormat(long datetime) {
        return getTimestampFormat().format(new Date(datetime));
    }

    /**
     * 将"年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串转换成Long型日期
     *
     * @param timestampStr 年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串
     * @return Long型日期
     */
    public static long formatTimestampToLong(String timestampStr) {
        try {
            return getTimestampFormat().parse(timestampStr).getTime();
        } catch (ParseException e) {
            return 0L;
        }
    }

    /**
     * 将"年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串转换成日期
     *
     * @param timestampStr 年年年年-月月-日日 时时:分分:秒秒"格式的日期字符串
     * @return 日期
     */
    public static Date formatTimestampToDate(String timestampStr) {
        try {
            return getTimestampFormat().parse(timestampStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将"年年年年-月月-日日"格式的日期字符串转换成日期
     *
     * @param dateStr 年年年年-月月-日日"格式的日期字符串
     * @return 日期
     */
    public static Date formatDateToDate(String dateStr) {
        try {
            return getDateFormat().parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 格式化成日期格式
     *
     * @param date 要格式化的日期
     * @return "年年年年-月月-日日"格式的日期字符串
     */
    public static String dateFormat(Date date) {
        if (date == null) {
            return "";
        }
        return getDateFormat().format(date);
    }

    /**
     * 格式化成日期格式
     *
     * @param date 要格式化的日期
     * @return "××××年××月××日"格式的日期字符串
     */
    public static String dateFormatCN(Date date) {
        if (date == null) {
            return "";
        }
        return getDateFormatCN().format(date);
    }

    /**
     * 格式化成日期格式
     *
     * @param datetime 要格式化的日期
     * @return "年年年年-月月-日日"格式的日期字符串
     */
    public static String dateFormat(long datetime) {
        return getDateFormat().format(new Date(datetime));
    }

    /**
     * 将"年年年年-月月-日日"格式的日期字符串转换成Long型日期
     *
     * @param dateStr "年年年年-月月-日日"格式的日期字符串
     * @return Long型日期
     */
    public static long formatDateToLong(String dateStr) {
        try {
            return getDateFormat().parse(dateStr).getTime();
        } catch (ParseException e) {
            return 0L;
        }
    }

    /**
     * 得到本月的第一天
     *
     * @return 以"年年年年-月月-日日"格式返回当前月第一天的日期
     */
    public static String getFirstDayOfCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return getDateFormat().format(calendar.getTime());
    }

    /**
     * 得到本月的最后一天
     *
     * @return 以"年年年年-月月-日日"格式返回当前月最后一天的日期
     */
    public static String getLastDayOfCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getDateFormat().format(calendar.getTime());
    }

    /**
     * 获取当前日期前（后）的某一天
     *
     * @param offset 偏移量，即当前日期之前（后）多少天，如果是之前，offset为负的整数
     * @return 以"年年年年-月月-日日"格式返回要获取的日期
     */
    public static Date getDayAfterCurrentDate(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, offset);
        return calendar.getTime();
    }

    /**
     * 根据指定的时间参数获取时间
     *
     * @param year   year
     * @param month  month
     * @param day    day
     * @param hour   hour
     * @param minute minute
     * @param second second
     * @return a Date()
     */
    public static Date getTimeByIdentifiedValues(Integer year, Integer month, Integer day, Integer hour,
                                                 Integer minute, Integer second) {
        Calendar calendarAdd = Calendar.getInstance();
        calendarAdd.setTime(new Date());

        calendarAdd.add(Calendar.DAY_OF_MONTH, month);
        calendarAdd.set(Calendar.HOUR_OF_DAY, day);
        calendarAdd.set(Calendar.MINUTE, minute);
        calendarAdd.set(Calendar.SECOND, second);
        return calendarAdd.getTime();
    }

    /**
     * 获取默认日期时间
     *
     * @return a Date()
     */
    public static Date getDefaultDateTime() {
        return new Date(formatTimestampToDate("1970-01-01 00:00:00").getTime());
    }

    public static Date getSimplifyDatePattern(String str) {
        return getDate(str, simplifyDatePattern);
    }

    private static Date getDate(String str, String format) {
        if (CommonUtils.isEmpty(str) || CommonUtils.isEmpty(format)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    private static final Pattern DATE_TYPE = Pattern
            .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        Matcher m = DATE_TYPE.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getSimplifyDatePattern(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(simplifyDatePattern);
        if (date == null) {
            return null;
        }
        return simpleDateFormat.format(date);
    }
}