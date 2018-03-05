package com.controller;

import com.common.result.Result;
import com.entity.SystemOpeningTime;
import com.service.SystemOpeningTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 系统开放时间控制器
 *
 * @author 潘根山
 * @create 2018-02-14 09:24
 * @since 1.0.0
 */
@Controller
@RequestMapping("/systemOpeningTime")
public class SystemOpeningTimeController {
    @Autowired
    private SystemOpeningTimeService systemOpeningTimeService;

    @RequestMapping("/")
    public String toSystemOpeningTime(Model model) {
        SystemOpeningTime systemOpeningTime = systemOpeningTimeService.getSystemOpeningTime();
        model.addAttribute("systemOpeningTime", systemOpeningTime);
        return "system_opening_time";
    }

    /**
     * 更新 系统开放时间
     *
     * @param systemOpeningTime startTime
     *                          endTime
     *                          id
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result updateSystemOpeningTime(@Valid SystemOpeningTime systemOpeningTime) {
        systemOpeningTimeService.updateSystemOpeningTime(systemOpeningTime);
        return Result.success(true);
    }
}