package com.dao;

import com.SpringBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WntdqkHzMapperTest extends SpringBaseTest {
    @Autowired
    private WntdqkHzMapper wntdqkHzMapper;

    @Test
    public void deleteWntdqkHz() {
        wntdqkHzMapper.deleteWntdqkHz();
    }

    @Test
    public void insertWntdqkHz() {
        wntdqkHzMapper.insertWntdqkHz("2017");
    }
}