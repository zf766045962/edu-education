package com.service.impl;

import com.dao.RoleMapper;
import com.entity.Role;
import com.service.RoleFunctionService;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-02-13 15:31
 * @since 1.0.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleFunctionService roleFunctionService;

    @Override
    public List<Role> listRole() {
        return roleMapper.listRole();
    }

    @Override
    public void deleteRoleById(Long roleId) {
        roleMapper.deleteRoleById(roleId);
        roleFunctionService.deleteByRoleId(roleId);
    }

    @Override
    public void insertSelective(Role role) {
        roleMapper.insertSelective(role);
    }

    @Override
    public void updateRoleName(Role role) {
        roleMapper.updateRoleName(role);
    }
}