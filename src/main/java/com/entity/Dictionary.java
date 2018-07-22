package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典表实体类
 *
 * @author 潘根山
 * @create 2018-07-22 20:05
 * @since 1.0.0
 */
public class Dictionary implements Serializable {
    private static final long serialVersionUID = -4617014842557051261L;
    private Long id;
    /**
     * 字典类型
     */
    private String type;
    /**
     * 字典编号
     */
    private String code;
    /**
     * 名称
     */
    private String name;

    private Date gmtCreate;

    private Date gmtModified;
    /**
     * 排序列
     */
    private int orderList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getOrderList() {
        return orderList;
    }

    public void setOrderList(int orderList) {
        this.orderList = orderList;
    }
}