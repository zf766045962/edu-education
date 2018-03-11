package com.controller.upload;

import com.common.Constants;
import com.common.result.CodeMsg;
import com.common.result.Result;
import com.util.normal.StringUtils;
import com.vo.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 潘根山
 * @create 2018-03-11 22:16
 * @since 1.0.0
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @RequestMapping("/")
    public Result upload(HttpServletRequest request, LoginUser loginUser) {
        DefaultMultipartHttpServletRequest defaultRequest = (DefaultMultipartHttpServletRequest) request;
        MultipartFile file = defaultRequest.getFile(Constants.FILE_NAME);
        if (null == file) {
            return Result.error(CodeMsg.FILE_DATA_EMPTY);
        }
        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            return Result.error(CodeMsg.FILE_DATA_EMPTY);
        }

        return Result.success(true);
    }
}
