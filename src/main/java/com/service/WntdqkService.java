package com.service;

import com.util.exception.CustomException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}
