package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 考生实体类
 *
 * @author 潘根山
 * @create 2018-03-11 18:27
 * @since 1.0.0
 */
public class Candidate implements Serializable {
    private static final long serialVersionUID = -5704194997700470232L;
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     *总分
     */
    private Integer totalScore;
    /**
     *排名
     */
    private Integer ranking;
    /**
     *物理
     */
    private Integer physics;
    /**
     *化学
     */
    private Integer chemistry;
    /**
     *生物
     */
    private Integer biology;
    /**
     *技术
     */
    private Integer technology;
    /**
     *历史
     */
    private Integer history;
    /**
     *地理
     */
    private Integer geography;
    /**
     *政治
     */
    private Integer politics;
    /**
     *联系电话
     */
    private String contactNumber;
    /**
     *咨询专家ID
     */
    private Long consultantId;
    /**
     *创建时间
     */
    private Date gmtCreate;
    /**
     *修改时间
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getPhysics() {
        return physics;
    }

    public void setPhysics(Integer physics) {
        this.physics = physics;
    }

    public Integer getChemistry() {
        return chemistry;
    }

    public void setChemistry(Integer chemistry) {
        this.chemistry = chemistry;
    }

    public Integer getBiology() {
        return biology;
    }

    public void setBiology(Integer biology) {
        this.biology = biology;
    }

    public Integer getTechnology() {
        return technology;
    }

    public void setTechnology(Integer technology) {
        this.technology = technology;
    }

    public Integer getHistory() {
        return history;
    }

    public void setHistory(Integer history) {
        this.history = history;
    }

    public Integer getGeography() {
        return geography;
    }

    public void setGeography(Integer geography) {
        this.geography = geography;
    }

    public Integer getPolitics() {
        return politics;
    }

    public void setPolitics(Integer politics) {
        this.politics = politics;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Long getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(Long consultantId) {
        this.consultantId = consultantId;
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
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalScore=" + totalScore +
                ", ranking=" + ranking +
                ", physics=" + physics +
                ", chemistry=" + chemistry +
                ", biology=" + biology +
                ", technology=" + technology +
                ", history=" + history +
                ", geography=" + geography +
                ", politics=" + politics +
                ", contactNumber='" + contactNumber + '\'' +
                ", consultantId=" + consultantId +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}