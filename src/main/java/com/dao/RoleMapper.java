package com.dao;

import com.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色表repository
 *
 * @author 潘根山
 * @create 2018-02-12 09:58
 * @since 1.0.0
 */
@Repository
public interface RoleMapper {
    /**
     * 查询所有角色信息
     *
     * @return 角色信息
     */
    @Select("select id,role_name from role")
    List<Role> listRole();

    /**
     * 根据id删除角色信息
     *
     * @param roleId 角色id
     */
    @Delete("delete from role where id=#{id}")
    void deleteRoleById(@Param("id") Long roleId);

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
     *             name
     */
    @Update("update role set role_name=#{roleName},gmt_modified=now() where id=#{id}")
    void updateRoleName(Role role);
}