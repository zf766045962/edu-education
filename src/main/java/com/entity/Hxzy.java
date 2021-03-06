package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 候选志愿实体类
 *
 * @author 潘根山
 * @create 2018-03-11 18:27
 * @since 1.0.0
 */
public class Hxzy implements Serializable {
    private static final long serialVersionUID = 527241088724250183L;
    private Long id;
    /**
     * 考试Id
     */
    private Long candidateId;
    /**
     * 学校代码
     */
    private String schoolCode;
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 专业代码
     */
    private String majorCode;
    /**
     * 专业名称
     */
    private String majorName;
    /**
     * 专业代码和省代码2拼接在一起
     */
    private String majorCodeNew;
    /**
     * 参考指数
     */
    private BigDecimal referenceIndex;
    /**
     * 志愿类型 1 冲 2稳 3保
     */
    private String status;
    /**
     * 学制代码
     */
    private String xzdm;
    /**
     * 所在地
     */
    private String szd;
    /**
     * 收费标准
     */
    private String sfbz;
    /**
     * 招生计划数
     */
    private int zsjhs;
    /**
     * 备注
     */
    private String bz;
    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public BigDecimal getReferenceIndex() {
        return referenceIndex;
    }

    public void setReferenceIndex(BigDecimal referenceIndex) {
        this.referenceIndex = referenceIndex;
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

    public String getMajorCodeNew() {
        return majorCodeNew;
    }

    public void setMajorCodeNew(String majorCodeNew) {
        this.majorCodeNew = majorCodeNew;
    }

    public String getXzdm() {
        return xzdm;
    }

    public void setXzdm(String xzdm) {
        this.xzdm = xzdm;
    }

    public String getSzd() {
        return szd;
    }

    public void setSzd(String szd) {
        this.szd = szd;
    }

    public String getSfbz() {
        return sfbz;
    }

    public void setSfbz(String sfbz) {
        this.sfbz = sfbz;
    }

    public int getZsjhs() {
        return zsjhs;
    }

    public void setZsjhs(int zsjhs) {
        this.zsjhs = zsjhs;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Override
    public String toString() {
        return "Hxzy{" +
                "id=" + id +
                ", candidateId=" + candidateId +
                ", schoolCode='" + schoolCode + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", majorCode='" + majorCode + '\'' +
                ", majorName='" + majorName + '\'' +
                ", majorCodeNew='" + majorCodeNew + '\'' +
                ", referenceIndex=" + referenceIndex +
                ", status='" + status + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}