package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 冲稳保比例实体类
 *
 * @author 潘根山
 * @create 2018-05-27 16:42
 * @since 1.0.0
 */
public class Cwbbl implements Serializable {
    private static final long serialVersionUID = 2840959259084599752L;
    private Integer id;
    /**
     * 冲区间比例
     */
    private Float chong;
    /**
     * 稳区间比例
     */
    private Float wen;
    /**
     * 保区间比例
     */
    private Float bao;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getChong() {
        return chong;
    }

    public void setChong(Float chong) {
        this.chong = chong;
    }

    public Float getWen() {
        return wen;
    }

    public void setWen(Float wen) {
        this.wen = wen;
    }

    public Float getBao() {
        return bao;
    }

    public void setBao(Float bao) {
        this.bao = bao;
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
        return "Cwbbl{" +
                "id=" + id +
                ", chong=" + chong +
                ", wen=" + wen +
                ", bao=" + bao +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}