package com.dao;

import com.SpringBaseTest;
import com.entity.Candidate;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-03-12 21:27
 * @since 1.0.0
 */
public class CandidateMapperTest extends SpringBaseTest {
    @Autowired
    private CandidateMapper candidateMapper;

    @Test
    public void listCandidateByCondition() {
        Candidate candidate = new Candidate();
        candidate.setConsultantId(1L);
        candidate.setName("潘");
        List<Candidate> candidates = candidateMapper.listCandidateByCondition(candidate);
        Assert.assertEquals(0, candidates.size());
    }

    @Test
    @Transactional
    public void deleteById() {
        candidateMapper.deleteById(1L);
    }
}