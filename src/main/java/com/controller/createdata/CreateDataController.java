package com.controller.createdata;

import com.common.result.Result;
import com.service.MajorService;
import com.service.RecruitStudentsPlanService;
import com.service.SchoolService;
import com.service.WntdqkHzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    private WntdqkHzService wntdqkHzService;
    @Autowired
    private RecruitStudentsPlanService recruitStudentsPlanService;

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

    /**
     * 生成投档数据
     *
     * @return 生成是否成功
     */
    @PostMapping("/wntdqkhz")
    @ResponseBody
    public Result createWntdqkHz(@RequestParam("year") String year) {
        wntdqkHzService.createWntdqkHz(year);
        return Result.success(true);
    }

    /**
     * 拆分投档数据
     *
     * @return 生成是否成功
     */
    @PostMapping("/wntdqk")
    @ResponseBody
    public Result splitWntdqkHz(@RequestParam("year") String year) {
        wntdqkHzService.splitWntdqk(year);
        return Result.success(true);
    }

    /**
     * 拆分招生计划库数据
     *
     * @return 生成是否成功
     */
    @PostMapping("/rsP")
    @ResponseBody
    public Result splitRecruitStudentsPlan(@RequestParam("year") String year) {
        recruitStudentsPlanService.splitRecruitStudentsPlan(year);
        return Result.success(true);
    }

    /**
     * 修改剩余招生计划数
     *
     * @return 生成是否成功
     */
    @PostMapping("/syzsjhs")
    @ResponseBody
    public Result generateSyzsjhs(@RequestParam("year") String year) {
        recruitStudentsPlanService.generateSyzsjhs(year);
        return Result.success(true);
    }
}