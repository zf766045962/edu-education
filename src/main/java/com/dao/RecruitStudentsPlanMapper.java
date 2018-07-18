package com.dao;

import com.entity.RecruitStudentsPlan;
import com.vo.RecruitStudentsPlanVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 计划表repository
 *
 * @author 潘根山
 * @create 2018-03-11 20:58
 * @since 1.0.0
 */
@Repository
public interface RecruitStudentsPlanMapper {
    /**
     * 批量插入计划数据
     *
     * @param list
     */
    void insertRecruitStudentsPlanBatch(List<RecruitStudentsPlan> list);

    /**
     * 根据条件计算 冲稳保学校数量
     *
     * @param map nf 年份
     *            szds 所在地集合
     *            kms 科目集合
     * @return 学校数量
     */
    int countSchool(Map<String, Object> map);

    /**
     * 根据条件计算 冲稳保专业数量
     *
     * @param map nf 年份
     *            szds 所在地集合
     *            kms 科目集合
     * @return 专业数量
     */
    int countMajor(Map<String, Object> map);

    /**
     * 根据条件查询 详细的专业情况
     *
     * @param map nf 年份
     *            szds 所在地集合
     *            kms 科目集合
     * @return 专业详情
     */
    List<RecruitStudentsPlanVo> listRecruitStudentsPlan(Map<String, Object> map);

    /**
     * 根据年份删除招生计划库数据
     *
     * @param nf 年份
     */
    @Delete("delete from recruit_students_plan where nf=#{nf}")
    void deleteRecruitStudentsPlan(String nf);

    /**
     * 根据备注查询专业信息
     *
     * @param year 年份
     * @return 专业信息
     */
    List<RecruitStudentsPlan> findZymc(@Param("year") String year);

    /**
     * 批量插入数据
     *
     * @param list
     */
    void insertRecruitStudentsPlanTempBatch(List<RecruitStudentsPlan> list);

    /**
     * 根据备注删除专业情况
     *
     * @param year 年份
     */
    void deleteRecruitStudentsPlanByBz(@Param("year") String year);

    /**
     * 将临时数据插入到原始表中
     */
    void insertTempToReal();

    /**
     * 删除临时表数据
     */
    @Delete("delete from recruit_students_plan_temp")
    void deleteRecruitStudentsPlanTemp();

    /**
     * 将符合条件的正式数据剩余招生计划数更新
     */
    void updateSyzsjhsByTemp(@Param("year") String year);

    /**
     * 将不符合条件的剩余招生计划数更新为0
     */
    void updateSyzsjhsToZero(@Param("year") String year);
}