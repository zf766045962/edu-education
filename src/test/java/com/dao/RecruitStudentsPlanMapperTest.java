package com.dao;

import com.SpringBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 潘根山
 * @create 2018-03-25 20:15
 * @since 1.0.0
 */
public class RecruitStudentsPlanMapperTest extends SpringBaseTest {
    @Autowired
    private RecruitStudentsPlanMapper recruitStudentsPlanMapper;

    @Test
    public void countSchool() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String s = simpleDateFormat.format(new Date());
        String nf = (Integer.valueOf(s) - 1) + "";
        Map<String, Object> map = new HashMap<>(4);
        List<String> kms = new ArrayList<>(3);
        kms.add("09");
        kms.add("05");
        kms.add("08");
        map.put("nf", nf);
        map.put("list", kms);
        map.put("min", 1000);
        map.put("max", 2000);
        int count = recruitStudentsPlanMapper.countSchool(map);
        System.out.println(count);
    }
}