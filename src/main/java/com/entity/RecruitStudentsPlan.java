package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 普高计划库
 *
 * @author 潘根山
 * @create 2018-03-11 20:44
 * @since 1.0.0
 */
public class RecruitStudentsPlan implements Serializable {
    private static final long serialVersionUID = -4024074748291136275L;
    private Long id;
    /**
     * 院校代码
     */
    private String yxdm;
    /**
     * 院校代号
     */
    private String yxdh;
    /**
     * 院校代号名称
     */
    private String yxdhmc;
    /**
     * 所在地
     */
    private String szd;
    /**
     * 是否985
     */
    private Integer sf985;
    /**
     * 是否211
     */
    private Integer sf211;

    private Integer bxlxdm;

    private String bxlxmc;
    /**
     * 省市代码
     */
    private Integer ssdm;
    /**
     * 省市名称
     */
    private String ssmc;

    private Integer zgdm;

    private String zgmc;

    private Integer ccdm;

    private String ccmc;
    /**
     * 专业代码
     */
    private String zydm;

    private String sbdm2;
    /**
     * 专业名称
     */
    private String zymc;

    private String xzdmxg;
    /**
     * 学制代码
     */
    private String xzdm;
    /**
     * 学制名称
     */
    private String xzmc;
    /**
     * 收费标准
     */
    private String sfbz;

    private String kldm;

    private String ksklmc;
    /**
     * 批次代码
     */
    private String pcdm;
    /**
     * 批次名称
     */
    private String pcmc;

    private String kslxdm;

    private String kslxmc;
    /**
     * 选考科目要求
     */
    private String xkkmyq;
    /**
     * 选考科目要求中文
     */
    private String xkkmyqzw;
    /**
     * 招生计划数
     */
    private Integer zsjhs;

    private String bz;

    private Date gmtCreate;

    private Date gmtModified;
    /**
     * 科目1
     */
    private String km1;
    /**
     * 科目2
     */
    private String km2;
    /**
     * 科目3
     */
    private String km3;
    /**
     * 专业代码与省代码2合成在一起
     */
    private String zydmNew;
    /**
     * 是否双一流
     */
    private String sfsyl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYxdm() {
        return yxdm;
    }

    public void setYxdm(String yxdm) {
        this.yxdm = yxdm;
    }

    public String getYxdh() {
        return yxdh;
    }

    public void setYxdh(String yxdh) {
        this.yxdh = yxdh;
    }

    public String getYxdhmc() {
        return yxdhmc;
    }

    public void setYxdhmc(String yxdhmc) {
        this.yxdhmc = yxdhmc;
    }

    public String getSzd() {
        return szd;
    }

    public void setSzd(String szd) {
        this.szd = szd;
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

    public Integer getBxlxdm() {
        return bxlxdm;
    }

    public void setBxlxdm(Integer bxlxdm) {
        this.bxlxdm = bxlxdm;
    }

    public String getBxlxmc() {
        return bxlxmc;
    }

    public void setBxlxmc(String bxlxmc) {
        this.bxlxmc = bxlxmc;
    }

    public Integer getSsdm() {
        return ssdm;
    }

    public void setSsdm(Integer ssdm) {
        this.ssdm = ssdm;
    }

    public String getSsmc() {
        return ssmc;
    }

    public void setSsmc(String ssmc) {
        this.ssmc = ssmc;
    }

    public Integer getZgdm() {
        return zgdm;
    }

    public void setZgdm(Integer zgdm) {
        this.zgdm = zgdm;
    }

    public String getZgmc() {
        return zgmc;
    }

    public void setZgmc(String zgmc) {
        this.zgmc = zgmc;
    }

    public Integer getCcdm() {
        return ccdm;
    }

    public void setCcdm(Integer ccdm) {
        this.ccdm = ccdm;
    }

    public String getCcmc() {
        return ccmc;
    }

    public void setCcmc(String ccmc) {
        this.ccmc = ccmc;
    }

    public String getZydm() {
        return zydm;
    }

    public void setZydm(String zydm) {
        this.zydm = zydm;
    }

    public String getSbdm2() {
        return sbdm2;
    }

    public void setSbdm2(String sbdm2) {
        this.sbdm2 = sbdm2;
    }

    public String getZymc() {
        return zymc;
    }

    public void setZymc(String zymc) {
        this.zymc = zymc;
    }

    public String getXzdmxg() {
        return xzdmxg;
    }

    public void setXzdmxg(String xzdmxg) {
        this.xzdmxg = xzdmxg;
    }

    public String getXzdm() {
        return xzdm;
    }

    public void setXzdm(String xzdm) {
        this.xzdm = xzdm;
    }

    public String getXzmc() {
        return xzmc;
    }

    public void setXzmc(String xzmc) {
        this.xzmc = xzmc;
    }

    public String getSfbz() {
        return sfbz;
    }

    public void setSfbz(String sfbz) {
        this.sfbz = sfbz;
    }

    public String getKldm() {
        return kldm;
    }

    public void setKldm(String kldm) {
        this.kldm = kldm;
    }

    public String getKsklmc() {
        return ksklmc;
    }

    public void setKsklmc(String ksklmc) {
        this.ksklmc = ksklmc;
    }

    public String getPcdm() {
        return pcdm;
    }

    public void setPcdm(String pcdm) {
        this.pcdm = pcdm;
    }

    public String getPcmc() {
        return pcmc;
    }

    public void setPcmc(String pcmc) {
        this.pcmc = pcmc;
    }

    public String getKslxdm() {
        return kslxdm;
    }

    public void setKslxdm(String kslxdm) {
        this.kslxdm = kslxdm;
    }

    public String getKslxmc() {
        return kslxmc;
    }

    public void setKslxmc(String kslxmc) {
        this.kslxmc = kslxmc;
    }

    public String getXkkmyq() {
        return xkkmyq;
    }

    public void setXkkmyq(String xkkmyq) {
        this.xkkmyq = xkkmyq;
    }

    public String getXkkmyqzw() {
        return xkkmyqzw;
    }

    public void setXkkmyqzw(String xkkmyqzw) {
        this.xkkmyqzw = xkkmyqzw;
    }

    public Integer getZsjhs() {
        return zsjhs;
    }

    public void setZsjhs(Integer zsjhs) {
        this.zsjhs = zsjhs;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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

    public String getKm1() {
        return km1;
    }

    public void setKm1(String km1) {
        this.km1 = km1;
    }

    public String getKm2() {
        return km2;
    }

    public void setKm2(String km2) {
        this.km2 = km2;
    }

    public String getKm3() {
        return km3;
    }

    public void setKm3(String km3) {
        this.km3 = km3;
    }

    public String getZydmNew() {
        return zydmNew;
    }

    public void setZydmNew(String zydmNew) {
        this.zydmNew = zydmNew;
    }

    public String getSfsyl() {
        return sfsyl;
    }

    public void setSfsyl(String sfsyl) {
        this.sfsyl = sfsyl;
    }

    @Override
    public String toString() {
        return "RecruitStudentsPlan{" +
                "id=" + id +
                ", yxdm='" + yxdm + '\'' +
                ", yxdh='" + yxdh + '\'' +
                ", yxdhmc='" + yxdhmc + '\'' +
                ", szd='" + szd + '\'' +
                ", sf985=" + sf985 +
                ", sf211=" + sf211 +
                ", bxlxdm=" + bxlxdm +
                ", bxlxmc='" + bxlxmc + '\'' +
                ", ssdm=" + ssdm +
                ", ssmc='" + ssmc + '\'' +
                ", zgdm=" + zgdm +
                ", zgmc='" + zgmc + '\'' +
                ", ccdm=" + ccdm +
                ", ccmc='" + ccmc + '\'' +
                ", zydm='" + zydm + '\'' +
                ", sbdm2='" + sbdm2 + '\'' +
                ", zymc='" + zymc + '\'' +
                ", xzdmxg='" + xzdmxg + '\'' +
                ", xzdm='" + xzdm + '\'' +
                ", xzmc='" + xzmc + '\'' +
                ", sfbz='" + sfbz + '\'' +
                ", kldm='" + kldm + '\'' +
                ", ksklmc='" + ksklmc + '\'' +
                ", pcdm='" + pcdm + '\'' +
                ", pcmc='" + pcmc + '\'' +
                ", kslxdm='" + kslxdm + '\'' +
                ", kslxmc='" + kslxmc + '\'' +
                ", xkkmyq='" + xkkmyq + '\'' +
                ", xkkmyqzw='" + xkkmyqzw + '\'' +
                ", zsjhs=" + zsjhs +
                ", bz='" + bz + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", km1='" + km1 + '\'' +
                ", km2='" + km2 + '\'' +
                ", km3='" + km3 + '\'' +
                ", zydmNew='" + zydmNew + '\'' +
                ", sfsyl='" + sfsyl + '\'' +
                '}';
    }
}