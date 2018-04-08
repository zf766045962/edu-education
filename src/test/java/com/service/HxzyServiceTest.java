package com.service;

import com.SpringBaseTest;
import com.dao.HxzyMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HxzyServiceTest extends SpringBaseTest {
    @Autowired
    private HxzyMapper hxzyMapper;

    @Test
    public void deleteByCandidateIdAndSchoolCodeAndMajorCode() {
        hxzyMapper.deleteByCandidateIdAndSchoolCodeAndMajorCode(1L, "22", "333");
    }
}