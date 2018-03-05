package com.dao;


import com.entity.SystemOpeningTime;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 系统开放时间repository
 *
 * @author 潘根山
 * @create 2018-02-12 09:58
 * @since 1.0.0
 */
@Repository
public interface SystemOpeningTimeMapper {
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
    @Select("update system_opening_time set start_time=#{startTime},end_time=#{endTime},gmt_modified=now() where id=#{id}")
    void updateSystemOpeningTime(SystemOpeningTime systemOpeningTime);
}