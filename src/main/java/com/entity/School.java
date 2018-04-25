package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 学校表实体类
 *
 * @author 潘根山
 * @create 2018-04-24 20:50
 * @since 1.0.0
 */
public class School implements Serializable {
    private static final long serialVersionUID = -1035881873230271869L;
    private Long id;
    /**
     * 院校代号
     */
    private String yxdh;
    /**
     * 院校名称
     */
    private String yxmc;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYxdh() {
        return yxdh;
    }

    public void setYxdh(String yxdh) {
        this.yxdh = yxdh;
    }

    public String getYxmc() {
        return yxmc;
    }

    public void setYxmc(String yxmc) {
        this.yxmc = yxmc;
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

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", yxdh='" + yxdh + '\'' +
                ", yxmc='" + yxmc + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}