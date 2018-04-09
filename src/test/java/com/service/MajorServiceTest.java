package com.service;

import com.SpringBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class MajorServiceTest extends SpringBaseTest {
    @Autowired
    private MajorService majorService;

    @Test
    public void listMajor() {
        majorService.listMajor("222");
    }
}