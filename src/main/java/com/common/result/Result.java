package com.common.result;

/**
 * Rest Api 返回
 *
 * @author 潘根山
 * @create 2018-01-19 09:06
 * @since 1.0.0
 */
public class Result<T> {
    private int code;

    private String msg;

    private T data;
    /**
     * 分页字段
     */
    private long count;

    private Result(T data) {
        this.code = CodeMsg.SUCCESS.getCode();
        this.msg = CodeMsg.SUCCESS.getMsg();
        this.data = data;
    }

    private Result(T data, long count) {
        this.code = CodeMsg.SUCCESS.getCode();
        this.msg = CodeMsg.SUCCESS.getMsg();
        this.count = count;
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<T>(cm);
    }

    public static <T> Result page(T data, long count) {
        return new Result<T>(data, count);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public long getCount() {
        return count;
    }
}