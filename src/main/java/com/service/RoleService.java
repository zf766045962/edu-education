package com.service;

import com.entity.Role;
import org.apache.ibatis.annotations.Select;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 潘根山
 * @create 2018-02-13 15:30
 * @since 1.0.0
 */
public interface RoleService {
    /**
     * 查询所有角色信息
     *
     * @return 角色信息
     */
    List<Role> listRole();

    /**
     * 删除角色信息
     *
     * @param roleId 角色id
     */
    void deleteRoleById(Long roleId);

    /**
     * 新增角色
     *
     * @param role
     */
    void insertSelective(Role role);

    /**
     * 更新角色名
     *
     * @param role id
     *             roleName
     */
    void updateRoleName(Role role);
}
