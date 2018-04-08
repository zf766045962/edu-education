package com.service.impl;

import com.dao.HxzyMapper;
import com.entity.Hxzy;
import com.service.HxzyService;
import com.vo.HxzyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-03-22 22:11
 * @since 1.0.0
 */
@Service
public class HxzyServiceImpl implements HxzyService {
    @Autowired
    private HxzyMapper hxzyMapper;

    @Override
    public int insertSelective(Hxzy record) {
        return hxzyMapper.insertSelective(record);
    }

    @Override
    public List<HxzyVo> listHxzyByCandidateId(Hxzy hxzy) {
        return hxzyMapper.listHxzyByCandidateId(hxzy);
    }

    @Override
    public void deleteAllByCandidateId(Long candidateId) {
        hxzyMapper.deleteAllByCandidateId(candidateId);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        hxzyMapper.deleteByIds(ids);
    }

    @Override
    public void deleteByCandidateIdAndSchoolCodeAndMajorCode(long candidateId, String schoolCode, String majorCode) {
        hxzyMapper.deleteByCandidateIdAndSchoolCodeAndMajorCode(candidateId, schoolCode, majorCode);
    }
}