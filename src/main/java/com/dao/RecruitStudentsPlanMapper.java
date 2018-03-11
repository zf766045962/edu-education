package com.dao;

import com.entity.RecruitStudentsPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 计划表repository
 *
 * @author 潘根山
 * @create 2018-03-11 20:58
 * @since 1.0.0
 */
@Repository
public interface RecruitStudentsPlanMapper {
    int insertSelective(RecruitStudentsPlan record);

    int updateByPrimaryKeySelective(RecruitStudentsPlan record);

    /**
     * 批量插入计划数据
     *
     * @param list
     */
    void insertRecruitStudentsPlanBatch(List<RecruitStudentsPlan> list);
}