package com.dao;

import com.entity.Wntdqk;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
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
    void updateDsms(@Param("year") String year);

    /**
     * 通过高考计划库，更新往年投档数据中的备注
     *
     * @param year     年份
     * @param planTime 招生计划库年份
     */
    void updateBz(@Param("year") String year, @Param("planTime") String planTime);

    /**
     * 通过备注查询专业信息
     *
     * @param year 年份
     */
    List<Wntdqk> findZymc(@Param("year") String year);

    /**
     * 删除临时数据
     */
    @Delete("truncate wntdqk_hz")
    void deleteWntdqkTemp();

    /**
     * 将临时数据插入到正式表中
     */
    void insertTempToReal();

    /**
     * 根据备注删除原始数据
     *
     * @param year 年份
     */
    void deleteWntdqkByBz(@Param("year") String year);

    /**
     * 将数据插入到临时表中
     *
     * @param tempList 往年投档数据临时表对象
     */
    void insertWntdqkTempBatch(List<Wntdqk> tempList);

    /**
     * 根据年份删除往年投档数据
     *
     * @param nf 年份
     */
    @Delete("delete from wntdqk where nf=#{nf}")
    void deleteByNf(@Param("nf") String nf);
}