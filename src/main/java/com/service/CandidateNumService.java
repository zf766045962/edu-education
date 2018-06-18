package com.service;

import com.entity.CandidateNum;

/**
 * @author 潘根山
 * @create 2018-06-03 09:28
 * @since 1.0.0
 */
public interface CandidateNumService {
    /**
     * 获取考生人数
     *
     * @return 考生人数实体类型
     */
    CandidateNum getCandidateNum();
    /**
     * 更新考生人数
     *
     * @param candidateNum num 考生人数
     * @return 更新是否成功
     */
    int updateCandidateNum(CandidateNum candidateNum);
}
