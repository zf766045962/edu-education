package com.dao;

import com.SpringBaseTest;
import com.entity.Province;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProvinceMapperTest extends SpringBaseTest {
    @Autowired
    private ProvinceMapper provinceMapper;

    @Test
    public void listProvince() {
        List<Province> provinces = provinceMapper.listProvince();
        provinces.forEach(System.out::println);
    }
}