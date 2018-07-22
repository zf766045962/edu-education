package com.dao;

import com.entity.Dictionary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典表repository
 *
 * @author 潘根山
 * @create 2018-05-27 16:48
 * @since 1.0.0
 */
@Repository
public interface DictionaryMapper {
    /**
     * 根据字典类型查询字典信息
     *
     * @param type 字典类型
     * @return 字典信息
     */
    List<Dictionary> listDictionary(String type);
}