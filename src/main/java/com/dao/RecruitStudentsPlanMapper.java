package com.dao;

import com.entity.RecruitStudentsPlan;
import com.vo.RecruitStudentsPlanVo;
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
}