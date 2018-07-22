package com.service;

import com.entity.Dictionary;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-07-22 20:22
 * @since 1.0.0
 */

public interface DictionaryService {
    /**
     * 根据字典类型查询字典信息
     *
     * @param type 字典类型
     * @return 字典信息
     */
    List<Dictionary> listDictionary(String type);
}