package com.vo;

import com.entity.RecruitStudentsPlan;

import java.math.BigDecimal;

/**
 * 招生计划库工具类
 *
 * @author 潘根山
 * @create 2018-03-26 21:55
 * @since 1.0.0
 */
public class RecruitStudentsPlanVo extends RecruitStudentsPlan {
    private static final long serialVersionUID = 4517863621227142306L;

    private BigDecimal ckzs;
    /**
     * 是否收藏 0 未收藏 1已收藏
     */
    private String sfsc;
    /**
     * 组合参数
     */
    private String zhcs;

    private String ckzsName;

    public BigDecimal getCkzs() {
        return ckzs;
    }

    public void setCkzs(BigDecimal ckzs) {
        this.ckzs = ckzs;
    }

    public String getSfsc() {
        return sfsc;
    }

    public void setSfsc(String sfsc) {
        this.sfsc = sfsc;
    }

    public String getZhcs() {
        return zhcs;
    }

    public void setZhcs(String zhcs) {
        this.zhcs = zhcs;
    }

    public String getCkzsName() {
        return ckzsName;
    }

    public void setCkzsName(String ckzsName) {
        this.ckzsName = ckzsName;
    }
}
