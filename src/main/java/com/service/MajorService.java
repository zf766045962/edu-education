package com.service;

import com.entity.Major;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-04-03 20:29
 * @since 1.0.0
 */
public interface MajorService {
    /**
     * 查询所有的专业信息
     *
     * @param zymc 专业名称
     * @return 专业信息
     */

    List<Major> listMajor(String zymc);
}
