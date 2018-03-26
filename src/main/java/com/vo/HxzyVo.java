package com.vo;

import com.entity.Hxzy;

/**候选志愿工具类型
 * @author 潘根山
 * @create 2018-03-25 12:03
 * @since 1.0.0
 */
public class HxzyVo extends Hxzy {
    private static final long serialVersionUID = -9152702084414830404L;
    /**
     * 志愿状态名
     */
    private String statusName;
    /**
     * 参考指数 格式化
     */
    private String referenceIndexName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getReferenceIndexName() {
        return referenceIndexName;
    }

    public void setReferenceIndexName(String referenceIndexName) {
        this.referenceIndexName = referenceIndexName;
    }
}