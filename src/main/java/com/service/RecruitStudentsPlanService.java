package com.service;

import com.entity.RecruitStudentsPlan;
import com.util.exception.CustomException;
import com.vo.RecruitStudentsPlanVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 潘根山
 * @create 2018-03-12 19:44
 * @since 1.0.0
 */
public interface RecruitStudentsPlanService {
    /**
     * 上传数据并插入到数据库中
     *
     * @param file file
     * @throws IOException     IOException
     * @throws CustomException CustomException
     */
    void uploadData(MultipartFile file, String nf) throws IOException, CustomException;

    /**
     * 根据条件计算 冲稳保学校数量
     *
     * @param map nf 年份
     *            szds 所在地集合
     *            kms 科目集合
     * @return 学校数量
     */
    int countSchool(Map<String, Object> map);

    /**
     * 根据条件计算 冲稳保专业数量
     *
     * @param map nf 年份
     *            szds 所在地集合
     *            kms 科目集合
     * @return 专业数量
     */
    int countMajor(Map<String, Object> map);

    /**
     * 根据条件查询 详细的专业情况
     *
     * @param map nf 年份
     *            szds 所在地集合
     *            kms 科目集合
     * @return 专业详情
     */
    List<RecruitStudentsPlanVo> listRecruitStudentsPlan(Map<String, Object> map);

    /**
     * 根据备注查询专业情况
     *
     * @param year 年份
     * @return 专业详情
     */
    List<RecruitStudentsPlan> findZymc(String year);

    /**
     * 拆分招生计划库数据
     *
     * @param year 年份
     */
    void splitRecruitStudentsPlan(String year);

    /**
     * 上传数据到临时表中
     *
     * @param file file
     */
    void uploadNewData(MultipartFile file) throws IOException, CustomException;

    /**
     * 修改剩余招生计划数
     *
     * @param year 年份
     */
    void generateSyzsjhs(String year);
}