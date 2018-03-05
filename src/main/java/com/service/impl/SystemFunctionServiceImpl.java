package com.service.impl;

import com.entity.SystemFunction;
import com.service.SystemFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-02-21 21:54
 * @since 1.0.0
 */
@Service
public class SystemFunctionServiceImpl implements SystemFunctionService {
    @Autowired
    private SystemFunctionService systemFunctionService;

    @Override
    public List<SystemFunction> listSystemFunction() {
        return systemFunctionService.listSystemFunction();
    }
}