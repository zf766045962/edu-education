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
     * @param sf211 是否985
     * @param sf985 是否211
     * @param sfsyl 是否双一流
     * @param bxlx 办学类型
     * @return 学校信息
     */

    List<School> listSchool(String yxmc,Integer sf985,Integer sf211,String sfsyl,Integer bxlx);

    /**
     * 初始化专业数据
     */
    void initializationSchool();
}
