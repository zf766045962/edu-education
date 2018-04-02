package com.dao;

import com.entity.Province;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 省市表repository
 *
 * @author 潘根山
 * @create 2018-03-22 22:01
 * @since 1.0.0
 */
@Repository
public interface ProvinceMapper {
    /**
     * 查询所有的省市信息
     *
     * @return 省市信息
     */
    List<Province> listProvince();
}