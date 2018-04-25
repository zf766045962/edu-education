package com.controller.lead;

import com.common.result.Result;
import com.entity.School;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 院校控制器
 *
 * @author 潘根山
 * @create 2018-04-08 19:48
 * @since 1.0.0
 */
@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/")
    @ResponseBody
    public Result findMajor(
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
            , @RequestParam(value = "currentPage", defaultValue = "1") int currentPage, String yxmc) {
        PageHelper.startPage(currentPage, pageSize);
        List<School> schoolList = schoolService.listSchool(yxmc);
        PageInfo<School> pageInfo = new PageInfo<>(schoolList);
        return Result.page(pageInfo.getList(), pageInfo.getTotal());
    }
}