package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 往年投档情况表
 *
 * @author 潘根山
 * @version 1.0
 * @create 2018-03-18 20:09
 */
public class Wntdqk implements Serializable {
    private static final long serialVersionUID = -6662270608396405057L;
    private Long id;
    /**
     * 学校代码
     */
    private String schooleCode;
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 专业代码
     */
    private String zydm;
    /**
     * 专业名称
     */
    private String zymc;
    /**
     * 科类
     */
    private String kl;
    /**
     * 年份
     */
    private String nf;
    /**
     * 投档人数
     */
    private Integer tdrs;
    /**
     * 平均分
     */
    private Integer pjf;
    /**
     * 最低分
     */
    private Integer zdf;
    /**
     * 投档名次
     */
    private Integer tdmc;
    /**
     * 批次
     */
    private Integer pc;
    /**
     * 参考指数
     */
    private BigDecimal ckzs;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchooleCode() {
        return schooleCode;
    }

    public void setSchooleCode(String schooleCode) {
        this.schooleCode = schooleCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getZydm() {
        return zydm;
    }

    public void setZydm(String zydm) {
        this.zydm = zydm;
    }

    public String getZymc() {
        return zymc;
    }

    public void setZymc(String zymc) {
        this.zymc = zymc;
    }

    public String getKl() {
        return kl;
    }

    public void setKl(String kl) {
        this.kl = kl;
    }

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    public Integer getTdrs() {
        return tdrs;
    }

    public void setTdrs(Integer tdrs) {
        this.tdrs = tdrs;
    }

    public Integer getPjf() {
        return pjf;
    }

    public void setPjf(Integer pjf) {
        this.pjf = pjf;
    }

    public Integer getZdf() {
        return zdf;
    }

    public void setZdf(Integer zdf) {
        this.zdf = zdf;
    }

    public Integer getTdmc() {
        return tdmc;
    }

    public void setTdmc(Integer tdmc) {
        this.tdmc = tdmc;
    }

    public Integer getPc() {
        return pc;
    }

    public void setPc(Integer pc) {
        this.pc = pc;
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

    public BigDecimal getCkzs() {
        return ckzs;
    }

    public void setCkzs(BigDecimal ckzs) {
        this.ckzs = ckzs;
    }

    @Override
    public String toString() {
        return "Wntdqk{" +
                "id=" + id +
                ", schooleCode='" + schooleCode + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", zydm='" + zydm + '\'' +
                ", zymc='" + zymc + '\'' +
                ", kl='" + kl + '\'' +
                ", nf='" + nf + '\'' +
                ", tdrs=" + tdrs +
                ", pjf=" + pjf +
                ", zdf=" + zdf +
                ", tdmc=" + tdmc +
                ", pc=" + pc +
                ", ckzs='" + ckzs + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}