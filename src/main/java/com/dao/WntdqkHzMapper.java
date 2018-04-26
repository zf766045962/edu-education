package com.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 往年投档汇总情况 repository
 *
 * @author 潘根山
 * @create 2018-04-26 21:16
 * @since 1.0.0
 */
@Repository
public interface WntdqkHzMapper {
    /**
     * 删除往年投档汇总数据
     */
    @Delete("delete from wntdqk_hz")
    void deleteWntdqkHz();

    /**
     * 插入制定年份的投档情况
     *
     * @param year 年份
     */
    void insertWntdqkHz(@Param("nf") String year);
}