package com.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 考生上线人数实体类
 *
 * @author 潘根山
 * @create 2018-06-03 09:23
 * @since 1.0.0
 */
public class CandidateNum implements Serializable {
    private static final long serialVersionUID = -4224291009317862320L;
    private Long id;
    /**
     * 上线人数
     */
    private Integer num;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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