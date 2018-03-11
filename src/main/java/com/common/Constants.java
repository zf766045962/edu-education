package com.common;

/**
 * 常量存放类
 *
 * @author pgs
 */
public class Constants {
    /**
     * 执行状态-成功
     */
    public static final String EXECUTE_STATE_SUCCESS = "成功";
    /**
     * 执行状态-失败
     */
    public static final String EXECUTE_STATE_FAIL = "失败";

    public static final String RUN_STATUS_PAUSE = "PAUSED";

    public static final String RUN_STATUS_STARTED = "RUN";

    public static final String DEFAULT_METHOD_NAME = "execute";

    public static final String DEFAULT_GROUP_NAME = "group";

    public static final String simplifyTimestampPattern = "yyyyMMddHHmmss";

    public static final String timestampPattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 定时器查询失败
     */
    public static final Integer SEARCH_FAIL = 0;
    /**
     * 定时器查询成功
     */
    public static final Integer SEARCH_SUCCESS = 1;

    /**
     * 方法返回值:成功
     */
    public static final String RESULT_SUCCESS = "操作成功";
    /**
     * 方法返回值:失败
     */
    public static final String RESULT_FALSE = "操作失败";

    /**
     * session name
     */
    public static final String SESSION_NAME = "user";
    /**
     * excel批量插入条数
     */
    public static final int EXCEL_BATCH_SIZE = 50;
    /**
     * 超级用户前缀 admin
     */
    public static final String USER_PREFIX = "admin";
}