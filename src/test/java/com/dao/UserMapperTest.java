package com.dao;

import com.SpringBaseTest;
import com.vo.UserVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 潘根山
 * @create 2018-03-12 22:06
 * @since 1.0.0
 */
public class UserMapperTest extends SpringBaseTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void getUserByName() {
        UserVo userVo = userMapper.getUserByName("jack");
        Assert.assertNotNull(userVo);
    }
}