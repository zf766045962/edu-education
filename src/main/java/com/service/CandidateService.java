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

    /**
     * 删除考生信息
     *
     * @param id 考生id
     */
    void deleteById(Long id);

    /**
     * 保存考生信息
     *
     * @param candidate 考生对象
     */
    void saveCandidate(Candidate candidate);

    /**
     * 用过id获取考生信息
     *
     * @param id id
     * @return 考生信息
     */
    Candidate getCandidateById(Long id);
}
