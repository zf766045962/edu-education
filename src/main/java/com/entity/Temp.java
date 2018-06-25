package com.entity;

import java.io.Serializable;

/**
 * 临时表用于导入2015、2016备注数据
 *
 * @author 潘根山
 * @version 1.0
 * @create 2018-02-12 09:55
 */
public class Temp implements Serializable {
    private static final long serialVersionUID = -4091175918987945273L;
    private Long id;

    private String pc;
    /**
     * 院校代码
     */
    private String yxdm;
    /**
     * 院校代码
     */
    private String yxmc;
    /**
     * 院校代码
     */
    private String yxlx;
    /**
     * 院校代码
     */
    private String kl;
    /**
     * 院校代码
     */
    private String zydm;
    /**
     * 院校代码
     */
    private String zydhmc;
    /**
     * 层次名称
     */
    private String ccmc;
    /**
     * 备注
     */
    private String bz;
    /**
     * 年份
     */
    private String nf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getYxdm() {
        return yxdm;
    }

    public void setYxdm(String yxdm) {
        this.yxdm = yxdm;
    }

    public String getYxmc() {
        return yxmc;
    }

    public void setYxmc(String yxmc) {
        this.yxmc = yxmc;
    }

    public String getYxlx() {
        return yxlx;
    }

    public void setYxlx(String yxlx) {
        this.yxlx = yxlx;
    }

    public String getKl() {
        return kl;
    }

    public void setKl(String kl) {
        this.kl = kl;
    }

    public String getZydm() {
        return zydm;
    }

    public void setZydm(String zydm) {
        this.zydm = zydm;
    }

    public String getZydhmc() {
        return zydhmc;
    }

    public void setZydhmc(String zydhmc) {
        this.zydhmc = zydhmc;
    }

    public String getCcmc() {
        return ccmc;
    }

    public void setCcmc(String ccmc) {
        this.ccmc = ccmc;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }
}