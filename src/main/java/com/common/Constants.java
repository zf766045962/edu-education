package com.common;

/**
 * 常量存放类
 *
 * @author pgs
 */
public interface Constants {
    public static final String SIMPLIFY_TIMESTAMP_PATTERN = "yyyyMMddHHmmss";

    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * session name
     */
    public static final String SESSION_NAME = "user";
    /**
     * excel批量插入条数
     */
    public static final int EXCEL_BATCH_SIZE = 1000;
    /**
     * 超级用户前缀 admin
     */
    public static final String USER_PREFIX = "admin";
    /**
     * 文件域名称
     */
    public static final String FILE_NAME = "file";
    /**
     * 普高计划数据
     */
    public static final String FILE_TYPE_PLAN = "1";
    /**
     * 投档数据
     */
    public static final String FILE_TYPE_SUBMIT = "2";
    /**
     * 新增普高计划数据-用于更新普高计划剩余招生人数
     */
    public static final String FILE_TYPE_PLAN_NEW = "3";

    static class Dictionary {
        /**
         * 学制代码
         */
        public static final String XZDM = "xzdm";
    }
}