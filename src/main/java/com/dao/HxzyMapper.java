package com.dao;

import com.entity.Hxzy;
import com.vo.HxzyVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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
     * @param hxzy 候选志愿信息
     * @return 考生候选志愿
     */
    List<HxzyVo> listHxzyByCandidateId(Hxzy hxzy);

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

    /**
     * 根据考生Id，学校代码，专业代码取消候选志愿
     *
     * @param candidateId    考生ID
     * @param schoolCode     学校代码
     * @param majorCode      专业代码
     * @param referenceIndex 参考指数
     */
    @Delete("delete from hxzy where candidate_id=#{candidateId} and school_code=#{schoolCode} and major_code_new=#{majorCode} and reference_index=#{referenceIndex}")
    void deleteByCandidateIdAndSchoolCodeAndMajorCode(
            @Param("candidateId") long candidateId
            , @Param("schoolCode") String schoolCode
            , @Param("majorCode") String majorCode
            , @Param("referenceIndex") BigDecimal referenceIndex);
}