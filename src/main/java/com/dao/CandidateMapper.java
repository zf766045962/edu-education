package com.dao;

import com.entity.Candidate;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 考生表repository
 *
 * @author 潘根山
 * @create 2018-03-11 18:26
 * @since 1.0.0
 */
@Repository
public interface CandidateMapper {
    int insertSelective(Candidate record);

    int updateByPrimaryKeySelective(Candidate record);

    /**
     * 考生集合
     *
     * @param candidate
     * @return
     */
    List<Candidate> listCandidateByCondition(Candidate candidate);

    /**
     * 删除考生信息
     *
     * @param id 考生主键
     */
    @Delete("delete from candidate where id=#{id}")
    void deleteById(@Param("id") Long id);

    /**
     * 新增考生信息
     *
     * @param candidate 考生对象
     */
    void saveCandidate(Candidate candidate);

    /**
     * 通过id获取考生信息
     *
     * @param id id
     * @return 考生信息
     */
    Candidate getCandidateById(Long id);
}