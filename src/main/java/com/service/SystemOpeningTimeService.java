package com.service;

import com.entity.SystemOpeningTime;
import org.apache.ibatis.annotations.Select;

/**
 * @author 潘根山
 * @create 2018-02-12 22:13
 * @since 1.0.0
 */
public interface SystemOpeningTimeService {
    /**
     * 获取系统开放时间
     *
     * @return 系统开放时间
     */
    SystemOpeningTime getSystemOpeningTime();

    /**
     * 修改系统开放时间
     *
     * @param systemOpeningTime startTime
     *                          endTime
     *                          id
     */
    void updateSystemOpeningTime(SystemOpeningTime systemOpeningTime);
}
