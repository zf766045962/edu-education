package com.service.impl;

import com.dao.SchoolMapper;
import com.entity.School;
import com.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-04-24 20:42
 * @since 1.0.0
 */
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public List<School> listSchool(String yxmc) {
        return schoolMapper.listSchool(yxmc);
    }

    @Override
    public void initializationSchool() {
        schoolMapper.deleteSchool();
        schoolMapper.insertSchool();
    }
}
