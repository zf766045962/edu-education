package com.service;

import com.entity.Wntdqk;
import com.util.exception.CustomException;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 潘根山
 * @create 2018-03-18 20:21
 * @since 1.0.0
 */
public interface WntdqkService {
    /**
     * 上传数据并插入到数据库中
     *
     * @param file file
     * @param year 数据年份
     * @throws IOException     IOException
     * @throws CustomException CustomException
     */
    void uploadData(MultipartFile file, String year) throws IOException, CustomException;

    /**
     * 生成段数描述的临时数据
     *
     * @param year 年份
     */
    void generateDsms(String year);

    /**
     * 更新往年投档数据表中的段数描述
     *
     * @param year 年份
     */
    void updateDsms(String year);

    /**
     * 通过高考计划库，更新往年投档数据中的备注
     *
     * @param year 年份
     */
    void updateBz(String year, String planTime);

    /**
     * 通过备注查询专业信息
     *
     * @param year 年份
     */
    List<Wntdqk> findZymc(String year);

    /**
     * 删除临时表数据
     */
    void deleteWntdqkTemp();

    /**
     * 将临时数插入到原始表中
     */
    void insertTempToReal();

    /**
     * 根据备注删除数据
     *
     * @param year 年份
     */
    void deleteWntdqkByBz(String year);

    void insertWntdqkTempBatch(List<Wntdqk> tempList);
}
