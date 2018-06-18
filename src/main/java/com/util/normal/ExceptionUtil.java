package com.util.normal;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常日志收集
 *
 * @author 潘根山
 * @create 2018-06-03 11:34
 * @since 1.0.0
 */
public class ExceptionUtil {
    /**
     * 收集异常日志
     *
     * @param e 异常
     * @return 日志信息
     */
    public static String collectExceptionStackMsg(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter, true));
        return stringWriter.toString();
    }
}