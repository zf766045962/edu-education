package com.service;

import com.entity.Candidate;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-03-12 21:26
 * @since 1.0.0
 */
public interface CandidateService {
    /**
     * 考生集合
     *
     * @param candidate
     * @return
     */
    List<Candidate> listCandidateByCondition(Candidate candidate);
}
