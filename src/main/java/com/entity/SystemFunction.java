package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统功能实体类
 *
 * @author 潘根山
 * @create 2018-02-12 09:58
 * @since 1.0.0
 */
public class SystemFunction implements Serializable {
    private static final long serialVersionUID = 4005182362944032250L;
    private Long id;

    private String functionCode;

    private String functionName;

    private String orderField;

    private String status;

    private Date gmtCreate;

    private Date gmtModified;

    private String functionUrl;

    private String parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getFunctionUrl() {
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    @Override
    public String toString() {
        return "SystemFunction{" +
                "id=" + id +
                ", functionCode='" + functionCode + '\'' +
                ", functionName='" + functionName + '\'' +
                ", orderField='" + orderField + '\'' +
                ", status='" + status + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", functionUrl='" + functionUrl + '\'' +
                '}';
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}