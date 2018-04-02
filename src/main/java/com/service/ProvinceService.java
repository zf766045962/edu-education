package com.service;

import com.entity.Province;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-04-02 20:52
 * @since 1.0.0
 */
public interface ProvinceService {
    /**
     * 查询所有的省市信息
     *
     * @return 省市信息
     */
    List<Province> listProvince();
}
