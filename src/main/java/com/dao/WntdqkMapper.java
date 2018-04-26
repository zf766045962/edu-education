package com.dao;

import com.entity.Wntdqk;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 往年投档情况 repository
 *
 * @author 潘根山
 * @create 2018-02-12 09:58
 * @since 1.0.0
 */
@Repository
public interface WntdqkMapper {
    /**
     * 批量插入往年投档情况
     *
     * @param list 往年投档情况
     */
    void insertWntdqkBatch(List<Wntdqk> list);
}