package com.service.impl;

import com.dao.RoleFunctionMapper;
import com.service.RoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 潘根山
 * @create 2018-02-13 15:30
 * @since 1.0.0
 */
@Service
public class RoleFunctionServiceImpl implements RoleFunctionService {
    @Autowired
    private RoleFunctionMapper roleFunctionMapper;

    @Override
    public int deleteByRoleId(Long roleId) {
        return roleFunctionMapper.deleteByRoleId(roleId);
    }

    @Override
    public List<String> findFunctionById(Long roleId) {
        return roleFunctionMapper.findFunctionById(roleId);
    }

    @Override
    public void updateFunctionCode(Long roleId, String[] codes) {
        roleFunctionMapper.deleteByRoleId(roleId);
        roleFunctionMapper.insertBatch(codes, roleId);
    }
}