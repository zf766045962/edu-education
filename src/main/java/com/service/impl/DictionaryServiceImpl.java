package com.service.impl;

import com.dao.DictionaryMapper;
import com.entity.Dictionary;
import com.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典表Service
 *
 * @author 潘根山
 * @create 2018-07-22 20:22
 * @since 1.0.0
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> listDictionary(String type) {
        return dictionaryMapper.listDictionary(type);
    }
}