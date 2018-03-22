package com.controller.lead;

import com.common.result.Result;
import com.entity.Candidate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.CandidateService;
import com.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 考生控制器
 *
 * @author 潘根山
 * @create 2018-03-12 21:09
 * @since 1.0.0
 */
@Controller
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @RequestMapping("/")
    public String toCandidate() {
        return "lead/candidate";
    }

    @RequestMapping("/search/{id}")
    public String toSearch(@PathVariable("id") Long id, Model model) {
        Candidate candidate = candidateService.getCandidateById(id);
        model.addAttribute("candidate", candidate);
        return "/lead/search";
    }

    /**
     * 查询考生列表
     *
     * @param loginUser   登录用户
     * @param candidate   考生对象
     * @param pageSize    每页显示的条数
     * @param currentPage 当前页
     * @return 考生列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result listCandidate(
            LoginUser loginUser
            , Candidate candidate
            , @RequestParam("pageSize") int pageSize
            , @RequestParam("currentPage") int currentPage) {
        candidate.setConsultantId(loginUser.getLoginId());
        PageHelper.startPage(currentPage, pageSize);
        List<Candidate> candidates = candidateService.listCandidateByCondition(candidate);
        PageInfo<Candidate> pageInfo = new PageInfo<>(candidates);
        return Result.page(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 删除考生信息
     *
     * @param id 考生id
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public Result deleteCandidate(@RequestParam("id") Long id) {
        candidateService.deleteById(id);
        return Result.success(true);
    }

    /**
     * 新增考生信息
     *
     * @param candidate 考生对象
     * @param loginUser 登录用户
     * @return result
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result addCandidate(@Valid Candidate candidate, LoginUser loginUser) {
        candidate.setConsultantId(loginUser.getLoginId());
        candidate.setGmtCreate(new Date());
        candidateService.saveCandidate(candidate);
        return Result.success(true);
    }

    /**
     * 查询志愿
     *
     * @param cMin 冲位次最小值
     * @param cMax 冲位次最大值
     * @param wMin 稳位次最小值
     * @param wMax 稳位次最大值
     * @param bMin 保位次最小值
     * @param bMax 保位次最大值
     * @param id   考生id
     * @return 志愿
     */
    @RequestMapping("/application")
    public Result searchApplication(
            @RequestParam("c_min") int cMin
            , @RequestParam("c_max") int cMax
            , @RequestParam("w_min") int wMin
            , @RequestParam("w_max") int wMax
            , @RequestParam("b_min") int bMin
            , @RequestParam("b_max") int bMax
            , @RequestParam("id") Long id) {
        //todo 查询考生信息
        //todo 查询冲志愿
        //todo 查询稳志愿
        //todo 查询保志愿
        //todo 根据过去三年的名次平均值进行计算
        return Result.success(true);
    }
}