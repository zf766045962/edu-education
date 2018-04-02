package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 省市实体类
 *
 * @author 潘根山
 * @create 2018-04-02 20:42
 * @since 1.0.0
 */
public class Province implements Serializable {
    private static final long serialVersionUID = 3316038059089194846L;
    private Long id;
    /**
     * 省市代码
     */
    private String code;
    /**
     * 省市名称
     */
    private String name;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}