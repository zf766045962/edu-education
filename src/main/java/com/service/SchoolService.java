package com.service;

import com.entity.School;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-04-23 20:39
 * @since 1.0.0
 */
public interface SchoolService {
    /**
     * 查询所有的学校信息
     *
     * @param yxmc 学校名称
     * @return 专业信息
     */

    List<School> listSchool(String yxmc);

    /**
     * 初始化专业数据
     */
    void initializationSchool();
}
