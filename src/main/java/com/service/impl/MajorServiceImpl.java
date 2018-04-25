package com.service.impl;

import com.dao.MajorMapper;
import com.entity.Major;
import com.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-04-17 07:19
 * @since 1.0.0
 */
@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorMapper majorMapper;

    @Override
    public List<Major> listMajor(String zymc) {
        return majorMapper.listMajor(zymc);
    }

    @Override
    public void initializationMajor() {
        majorMapper.deleteMajor();
        majorMapper.insertMajor();
    }
}