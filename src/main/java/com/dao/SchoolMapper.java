package com.dao;

import com.entity.School;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学校表repository
 *
 * @author 潘根山
 * @create 2018-04-24 20:39
 * @since 1.0.0
 */
@Repository
public interface SchoolMapper {
    /**
     * 新增专业数据
     */
    void insertSchool();

    /**
     * 删除专业信息
     */
    @Delete("delete from school")
    void deleteSchool();

    /**
     * 查询学校信息
     *
     * @param yxmc 院校名称
     * @param sf211 是否985
     * @param sf985 是否211
     * @param sfsyl 是否双一流
     * @param bxlx 办学类型
     * @return 学校信息
     */
    List<School> listSchool(
            @Param("yxmc") String yxmc
            ,@Param("sf985") Integer sf985
            ,@Param("sf211") Integer sf211
            ,@Param("sfsyl") String sfsyl
            ,@Param("bxlx") Integer bxlx);
}