package com.dao;

import com.entity.Temp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 临时表 repository
 *
 * @author 潘根山
 * @create 2018-02-12 09:58
 * @since 1.0.0
 */
@Repository
public interface TempMapper {
    /**
     * 批量插入临时表数据
     *
     * @param tempList 临时表数据
     */
    void insertBatch(List<Temp> tempList);
}