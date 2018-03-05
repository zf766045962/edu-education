package com.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-02-13 15:30
 * @since 1.0.0
 */
public interface RoleFunctionService {
    /**
     * 根据角色id删除关联表数据
     *
     * @param roleId 角色id
     */
    int deleteByRoleId(Long roleId);

    /**
     * 通过roleId 查询功能编码
     *
     * @param roleId 角色id
     * @return 功能编码集合
     */
    List<String> findFunctionById(@Param("roleId") Long roleId);

    /**
     * 更新角色的系统功能
     *
     * @param roleId 角色id
     * @param codes  系统功能数组
     */
    void updateFunctionCode(Long roleId, String[] codes);
}