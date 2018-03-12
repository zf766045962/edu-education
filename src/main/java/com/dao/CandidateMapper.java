package com.dao;

import com.entity.Candidate;
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
    int deleteByPrimaryKey(Long id);

    int insertSelective(Candidate record);

    Candidate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Candidate record);

    /**
     * 考生集合
     *
     * @param candidate
     * @return
     */
    List<Candidate> listCandidateByCondition(Candidate candidate);
}