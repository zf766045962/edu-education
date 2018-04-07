package com.service.impl;

import com.dao.MajorMapper;
import com.entity.Major;
import com.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorMapper majorMapper;

    @Override
    public List<Major> listMajor(String zymc) {
        return majorMapper.listMajor(zymc);
    }
}