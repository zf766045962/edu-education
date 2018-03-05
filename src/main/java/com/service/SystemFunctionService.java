package com.service;

import com.entity.SystemFunction;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-02-21 21:54
 * @since 1.0.0
 */
public interface SystemFunctionService {
    /**
     * 查询所有的系统功能
     *
     * @return 系统功能集合
     */
    List<SystemFunction> listSystemFunction();
}
