package com.service.impl;

import com.dao.SystemOpeningTimeMapper;
import com.entity.SystemOpeningTime;
import com.service.SystemOpeningTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 潘根山
 * @create 2018-02-12 22:14
 * @since 1.0.0
 */
@Service
public class SystemOpeningTimeServiceImpl implements SystemOpeningTimeService {
    @Autowired
    private SystemOpeningTimeMapper systemOpeningTimeMapper;

    @Override
    public SystemOpeningTime getSystemOpeningTime() {
        return systemOpeningTimeMapper.getSystemOpeningTime();
    }

    @Override
    public void updateSystemOpeningTime(SystemOpeningTime systemOpeningTime) {
        systemOpeningTimeMapper.updateSystemOpeningTime(systemOpeningTime);
    }
}