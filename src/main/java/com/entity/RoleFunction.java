package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色功能关联实体类
 *
 * @author 潘根山
 * @create 2018-02-12 09:58
 * @since 1.0.0
 */
public class RoleFunction implements Serializable {
    private static final long serialVersionUID = -8782931687366807504L;
    private Long id;

    private Long roleId;

    private Long functionId;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}