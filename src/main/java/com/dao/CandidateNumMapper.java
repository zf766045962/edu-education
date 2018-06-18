package com.dao;

import com.entity.CandidateNum;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateNumMapper {
    /**
     * 获取考生人数
     *
     * @return 考生人数实体类
     */
    @Select("select id,num from candidate_num")
    CandidateNum getCandidateNum();

    /**
     * 更新考生人数
     *
     * @param candidateNum num 考生人数
     * @return 更新是否成功
     */
    int updateCandidateNum(CandidateNum candidateNum);
}