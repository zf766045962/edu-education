package com.vo;

import java.io.Serializable;

/**
 * 系统功能以树的形式展现
 *
 * @author 潘根山
 * @create 2018-02-21 09:58
 * @since 1.0.0
 */
public class TreeVo implements Serializable {
    private static final long serialVersionUID = 5413630237489379856L;
    private Long id;

    private String name;

    private int pId;

    private boolean open;

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

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
