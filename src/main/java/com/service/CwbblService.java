package com.service;

import com.entity.Cwbbl;
/**
 * @author 潘根山
 * @create 2018-05-27 16:57
 * @since 1.0.0
 */
public interface CwbblService {
    /**
     * 获取冲稳保区间参数
     * @return 冲稳保区间参数
     */
    Cwbbl getCwbbl();

    /**
     * 更新冲稳保区间参数
     * @param cwbbl 冲稳保区间参数
     * @return 更新是否成功
     */
    int updateCwbbl(Cwbbl cwbbl);
}
