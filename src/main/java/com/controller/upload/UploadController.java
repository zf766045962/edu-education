package com.controller.upload;

import com.common.Constants;
import com.common.result.CodeMsg;
import com.common.result.Result;
import com.service.RecruitStudentsPlanService;
import com.service.WntdqkService;
import com.util.exception.CustomException;
import com.util.normal.StringUtils;
import com.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 潘根山
 * @create 2018-03-11 22:16
 * @since 1.0.0
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private RecruitStudentsPlanService recruitStudentsPlanService;

    @Autowired
    private WntdqkService wntdqkService;

    @RequestMapping("/")
    public String toUpload() {
        return "upload/upload";
    }

    /**
     * 上传数据
     *
     * @param request   request
     * @param loginUser 判断是否登录
     * @param fileType  文件类型
     * @return 统一api返回
     * @throws IOException     IOException
     * @throws CustomException CustomException
     */
    @RequestMapping("/excel")
    @ResponseBody
    public Result upload(HttpServletRequest request, LoginUser loginUser, @RequestParam("fileType") String fileType, String year) throws IOException, CustomException {
        DefaultMultipartHttpServletRequest defaultRequest = (DefaultMultipartHttpServletRequest) request;
        MultipartFile file = defaultRequest.getFile(Constants.FILE_NAME);
        if (null == file) {
            return Result.error(CodeMsg.FILE_DATA_EMPTY);
        }
        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            return Result.error(CodeMsg.FILE_DATA_EMPTY);
        }
        if (Constants.FILE_TYPE_PLAN.equals(fileType)) {
            recruitStudentsPlanService.uploadData(file);
        }
        if (Constants.FILE_TYPE_SUBMIT.equals(fileType)) {
            wntdqkService.uploadData(file, year);
        }
        return Result.success(true);
    }
}