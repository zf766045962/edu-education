package com.dao;

import com.entity.SystemFunction;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统功能repository
 *
 * @author 潘根山
 * @create 2018-02-12 09:58
 * @since 1.0.0
 */
@Repository
public interface SystemFunctionMapper {
    /**
     * 通过roleId 获取用户可以使用的功能
     *
     * @param roleId 角色id
     * @return 系统功能
     */
    List<SystemFunction> listSystemFunctionByRoleId(long roleId);

    /**
     * 查询所有的系统功能
     *
     * @return 系统功能集合
     */
    @Select("select id,function_code,function_name,parent_id from system_function order by order_field")
    List<SystemFunction> listSystemFunction();
}