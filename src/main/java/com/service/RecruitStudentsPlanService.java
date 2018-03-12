package com.service;

import com.util.exception.CustomException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    void uploadData(MultipartFile file) throws IOException, CustomException;
}