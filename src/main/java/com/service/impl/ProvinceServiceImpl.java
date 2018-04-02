package com.service.impl;

import com.dao.ProvinceMapper;
import com.entity.Province;
import com.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public List<Province> listProvince() {
        return provinceMapper.listProvince();
    }
}
