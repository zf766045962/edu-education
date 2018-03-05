package com.vo;

import com.entity.User;

/**
 * 用户工具类
 *
 * @author 潘根山
 * @create 2018-02-13 14:16
 * @since 1.0.0
 */
public class UserVo extends User {
    private static final long serialVersionUID = 2375656372587618649L;

    private String roleName;

    private String statusName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}