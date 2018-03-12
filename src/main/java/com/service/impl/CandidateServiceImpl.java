package com.service.impl;

import com.dao.CandidateMapper;
import com.entity.Candidate;
import com.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-03-12 21:26
 * @since 1.0.0
 */
@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public List<Candidate> listCandidateByCondition(Candidate candidate) {
        return candidateMapper.listCandidateByCondition(candidate);
    }
}