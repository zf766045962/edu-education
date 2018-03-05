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
     * 文件类型为国税分行业分税种
     */
    public static final String FILE_TYPE_GSHY = "1";

    /**
     * 文件类型为地税分行业分税种
     */
    public static final String FILE_TYPE_DSHY = "2";
    /**
     * 国税分行业分税种文件名前缀
     */
    public static final String FILE_GSHY_PREFIX = "GSHY";
    /**
     * 地税分行业分税种文件名前缀
     */
    public static final String FILE_DSHY_PREFIX = "DSHY";


    /**
     * 文件类型为收支存简表
     */
    public static final String FILE_TYPE_SZCJ = "3";

    /**
     * 收支存简表文件名前缀
     */
    public static final String FILE_SZCJ_PREFIX = "SZCJ";


    /**
     * 文件类型为收支存明细表
     */
    public static final String FILE_TYPE_SZCMX = "4";
    /**
     * 收支存明细表文件名前缀
     */
    public static final String FILE_SZCMX_PREFIX = "SZCMX";

    /**
     * 预算支出
     */
    public static final String FILE_TYPE_YSKMZC = "5";

    /**
     * 预算支出表前缀
     */
    public static final String FILE_YSKMZC_PREFIX = "YSKMZC";

    /**
     * 预算 收入
     */
    public static final String FILE_TYPE_YSKMSR = "6";

    /**
     * 预算收入表前缀
     */
    public static final String FILE_YSKMSR_PREFIX = "YSKMSR";


    /**
     * tmis电子税票
     */
    public static final String FILE_DZSP_YSKMSR = "7";

    /**
     * tmis电子税票前缀
     */
    public static final String FILE_DZSP_PREFIX = "DZSP";
    /**
     * 横向联网子系统税收明细数据
     */
    public static final String FILE_SSMX_PREFIX = "SSMX";
    /**
     * 地区类型 1(区县、乡镇) 2（市、区县、乡镇）3（市）4（中央）
     */
    public static final String DQLX_1 = "1";
    public static final String DQLX_2 = "2";
    public static final String DQLX_3 = "3";
    public static final String DQLX_4 = "4";
    /**
     * 文件上传校验方式 A-B.txt/xlsx/xls
     */
    public static final String CHECK_TYPE_1 = "1";
    /**
     * 文件上传校验方式 A-B-C.txt/xlsx/xls
     */
    public static final String CHECK_TYPE_2 = "2";
    /**
     * 上传路径
     */
    public static final String UPLOAD_PATH = "/template/upload";
    /**
     * 文件后缀
     */
    public static final String FILE_SUFFIX_TXT = "txt";
    /**
     * 解析状态 未提交
     */
    public static final String PARSE_UNCOMMITTED = "1";
    /**
     * 解析状态 未解析
     */
    public static final String PARSE_UN = "2";
    /**
     * 解析状态 解析中
     */
    public static final String PARSE_ON = "3";
    /**
     * 解析状态 解析成功
     */
    public static final String PARSE_SUCCESS = "4";
    /**
     * 解析状态 解析失败
     */
    public static final String PARSE_FAIL = "5";
    /**
     * 解析状态 删除文件
     */
    public static final String PARSE_DELETE = "6";
    /**
     * 超级用户前缀 admin
     */
    public static final String USER_PREFIX = "admin";
}