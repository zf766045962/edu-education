package com.dao;

import com.entity.Hxzy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 候选志愿repository
 *
 * @author 潘根山
 * @create 2018-03-22 22:01
 * @since 1.0.0
 */
@Repository
public interface HxzyMapper {
    /**
     * 新增候选志愿
     *
     * @param record Hxzy
     * @return 新增是否成功
     */
    int insertSelective(Hxzy record);

    /**
     * 通过考生ID查询考生候选志愿情况
     *
     * @param candidateId 考生ID
     * @return 考生候选志愿
     */
    List<Hxzy> listHxzyByCandidateId(Long candidateId);

    /**
     * 清空考生的候选志愿
     *
     * @param candidateId 考生ID
     */
    void deleteAllByCandidateId(Long candidateId);

    /**
     * 删除多个候选志愿
     *
     * @param ids 候选志愿集合
     */
    void deleteByIds(List<Long> ids);
}