package com.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色功能关联表
 *
 * @author 潘根山
 * @create 2018-02-12 09:58
 * @since 1.0.0
 */
@Repository
public interface RoleFunctionMapper {
    /**
     * 根据角色id删除关联表数据
     *
     * @param roleId 角色id
     */
    @Delete("delete from role_function where role_id=#{roleId}")
    int deleteByRoleId(@Param("roleId") Long roleId);

    /**
     * 通过roleId 查询功能编码
     *
     * @param roleId 角色id
     * @return 功能编码集合
     */
    @Select("select function_code from role_function where role_id=#{roleId}")
    List<String> findFunctionById(@Param("roleId") Long roleId);

    /**
     * 新增角色系统功能
     *
     * @param codes  功能数组
     * @param roleId 角色id
     */
    void insertBatch(@Param("array") String[] codes, @Param("roleId") Long roleId);
}