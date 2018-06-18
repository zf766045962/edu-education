package com.service.impl;

import com.dao.CandidateNumMapper;
import com.entity.CandidateNum;
import com.service.CandidateNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 潘根山
 * @create 2018-06-03 09:30
 * @since 1.0.0
 */
@Service
public class CandidateNumServiceImpl implements CandidateNumService {
    @Autowired
    private CandidateNumMapper candidateNumMapper;

    @Override
    public CandidateNum getCandidateNum() {
        return candidateNumMapper.getCandidateNum();
    }

    @Override
    public int updateCandidateNum(CandidateNum candidateNum) {
        candidateNum.setGmtModified(new Date());
        return candidateNumMapper.updateCandidateNum(candidateNum);
    }
}