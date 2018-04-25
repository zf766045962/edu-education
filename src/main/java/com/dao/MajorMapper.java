package com.dao;

import com.entity.Major;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 专业表repository
 *
 * @author 潘根山
 * @create 2018-03-22 22:01
 * @since 1.0.0
 */
@Repository
public interface MajorMapper {
    /**
     * 查询所有的专业信息
     *
     * @param zymc 专业名称
     * @return 专业信息
     */

    List<Major> listMajor(@Param("zymc") String zymc);

    /**
     * 新增专业数据
     */
    void insertMajor();

    /**
     * 删除专业信息
     */
    @Delete("delete from major")
    void deleteMajor();
}