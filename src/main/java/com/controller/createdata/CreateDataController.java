package com.controller.createdata;

import com.common.result.Result;
import com.service.MajorService;
import com.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 数据生成控制器
 *
 * @author 潘根山
 * @create 2018-03-12 21:09
 * @since 1.0.0
 */
@Controller
@RequestMapping("/createData")
public class CreateDataController {
    @Autowired
    private MajorService majorService;
    @Autowired
    private SchoolService schoolService;

    @RequestMapping("/")
    public String toCreateData() {
        return "/createdata/create_data";
    }

    /**
     * 生成专业数据
     *
     * @return 生成是否成功
     */
    @PostMapping("/major")
    @ResponseBody
    public Result createMajor() {
        majorService.initializationMajor();
        return Result.success(true);
    }

    /**
     * 生成学校数据
     *
     * @return 生成是否成功
     */
    @PostMapping("/school")
    @ResponseBody
    public Result createSchool() {
        schoolService.initializationSchool();
        return Result.success(true);
    }
}