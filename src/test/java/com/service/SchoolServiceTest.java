package com.service;

import com.SpringBaseTest;
import com.entity.School;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SchoolServiceTest extends SpringBaseTest {
    @Autowired
    private SchoolService schoolService;

    @Test
    public void listSchool() {
        List<School> schools = schoolService.listSchool("北京大学");
        schools.forEach(System.out::print);
    }

    @Test
    public void initializationSchool() {
        schoolService.initializationSchool();
    }
}