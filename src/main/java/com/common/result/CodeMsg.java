package com.common.result;

/**
 * 用于添加不同的返回状态
 *
 * @author 潘根山
 * @create 2018-01-19 09:09
 * @since 1.0.0
 */
public class CodeMsg {
    private int code;

    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(90000, "success");

    public static CodeMsg OPERATION_ERROR = new CodeMsg(91001, "操作失败");
    /**
     * 统一异常
     */
    public static CodeMsg BIND_ERROR = new CodeMsg(2000, "参数校验异常:%s");

    public static CodeMsg PARSE_ERROR = new CodeMsg(3000, "文件解析异常:%s");

    public static CodeMsg SERVER_ERROR = new CodeMsg(4000, "服务异常");

    /**
     * 文件上传异常 200xx
     */
    public static CodeMsg FILE_DATA_EMPTY = new CodeMsg(20002, "获取文件参数失败");
    /**
     * 登录异常 300xx
     */
    public static CodeMsg OLD_PASSWORD_ERROR = new CodeMsg(30001, "原密码错误");

    public static CodeMsg LOGIN_VALID = new CodeMsg(30002, "登录信息已失效,请重新登录！");

    public static CodeMsg USER_NOT_EXISTS = new CodeMsg(30003, "用户不存在");

    public static CodeMsg LOGIN_PASSWORD_ERROR = new CodeMsg(30004, "用户名或密码错误!");
    /**
     * 系统维护 400xx
     */
    public static CodeMsg USERNAME_EXIST = new CodeMsg(40001, "用户名已存在");

    public static CodeMsg SYSTEM_NOT_OPEN = new CodeMsg(40002, "系统未开放");

    public static CodeMsg EMPTY_FUNCTION_CODE = new CodeMsg(40003, "系统功能为空");

    /**
     * 考生异常 500xx
     */
    public static CodeMsg CANDIDATE_NOT_EXISTS = new CodeMsg(50001, "考生不存在");
    public static CodeMsg CANDIDATE_NUM_UNVALID = new CodeMsg(50002, "考生人数不合法");
    public static CodeMsg CANDIDATE_NUM_NOT_EXISTS = new CodeMsg(50002, "考生人数未设置");


    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}