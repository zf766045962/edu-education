package com.dao;

import com.entity.Wntdqk;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 往年投档情况 repository
 *
 * @author 潘根山
 * @create 2018-02-12 09:58
 * @since 1.0.0
 */
@Repository
public interface WntdqkMapper {
    /**
     * 批量插入往年投档情况
     *
     * @param list 往年投档情况
     */
    void insertWntdqkBatch(List<Wntdqk> list);

    /**
     * 生成段数描述的临时数据
     *
     * @param year 年份
     */
    void generateDsms(String year);

    /**
     * 更新往年投档数据表中的段数描述
     *
     * @param year 年份
     */
    void updateDsms(String year);

    /**
     * 通过高考计划库，更新往年投档数据中的备注
     *
     * @param year 年份
     */
    void updateBz(String year);
}