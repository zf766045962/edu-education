package com.dao;

import com.entity.Cwbbl;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 冲稳保比例repository
 *
 * @author 潘根山
 * @create 2018-05-27 16:48
 * @since 1.0.0
 */
@Repository
public interface CwbblMapper {
    /**
     * 获取冲稳保区间参数
     * @return 冲稳保区间参数
     */
    @Select("select id,chong,wen,bao from cwbbl limit 1")
    Cwbbl getCwbbl();

    /**
     * 更新冲稳保区间参数
     * @param cwbbl 冲稳保区间参数
     * @return 更新是否成功
     */
    int updateCwbbl(Cwbbl cwbbl);
}