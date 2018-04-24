package com.controller.lead;

import com.common.result.Result;
import com.entity.Major;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 专业控制器
 *
 * @author 潘根山
 * @create 2018-04-08 19:48
 * @since 1.0.0
 */
@Controller
@RequestMapping("/major")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @GetMapping("/")
    @ResponseBody
    public Result findMajor(
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
            , @RequestParam(value = "currentPage", defaultValue = "1") int currentPage, String zymc) {
        PageHelper.startPage(currentPage, pageSize);
        List<Major> majorList = majorService.listMajor(zymc);
        PageInfo<Major> pageInfo = new PageInfo<>(majorList);
        return Result.page(pageInfo.getList(), pageInfo.getTotal());
    }
}