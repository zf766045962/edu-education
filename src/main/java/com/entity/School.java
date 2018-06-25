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
     * 985
     */
    private Integer sf985;
    /**
     * 211
     */
    private Integer sf211;
    /**
     * 双一流
     */
    private String sfsyl;
    /**
     * 办学类型
     */
    private Integer bxlx;
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

    public Integer getSf985() {
        return sf985;
    }

    public void setSf985(Integer sf985) {
        this.sf985 = sf985;
    }

    public Integer getSf211() {
        return sf211;
    }

    public void setSf211(Integer sf211) {
        this.sf211 = sf211;
    }

    public String getSfsyl() {
        return sfsyl;
    }

    public void setSfsyl(String sfsyl) {
        this.sfsyl = sfsyl;
    }

    public Integer getBxlx() {
        return bxlx;
    }

    public void setBxlx(Integer bxlx) {
        this.bxlx = bxlx;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", yxdh='" + yxdh + '\'' +
                ", yxmc='" + yxmc + '\'' +
                ", sf985='" + sf985 + '\'' +
                ", sf211='" + sf211 + '\'' +
                ", sfsyl='" + sfsyl + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}