package com.vo;

import java.io.Serializable;

/**
 * 用作session user 防止与原有的用户对象冲突
 *
 * @author 潘根山
 * @create 2018-02-12 09:58
 * @since 1.0.0
 */
public class LoginUser implements Serializable {
    private static final long serialVersionUID = -5574220368476664539L;
    private long loginId;
    private String loginUserName;

    private long loginRoleId;

    private String loginNickName;

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public long getLoginRoleId() {
        return loginRoleId;
    }

    public void setLoginRoleId(long loginRoleId) {
        this.loginRoleId = loginRoleId;
    }

    public String getLoginNickName() {
        return loginNickName;
    }

    public void setLoginNickName(String loginNickName) {
        this.loginNickName = loginNickName;
    }

    public long getLoginId() {
        return loginId;
    }

    public void setLoginId(long loginId) {
        this.loginId = loginId;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "loginId=" + loginId +
                ", loginUserName='" + loginUserName + '\'' +
                ", loginRoleId=" + loginRoleId +
                ", loginNickName='" + loginNickName + '\'' +
                '}';
    }
}