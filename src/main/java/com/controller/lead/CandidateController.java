package com.controller.lead;

import com.common.result.CodeMsg;
import com.common.result.Result;
import com.entity.Candidate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.CandidateService;
import com.service.RecruitStudentsPlanService;
import com.vo.LoginUser;
import com.vo.RecruitStudentsPlanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private RecruitStudentsPlanService recruitStudentsPlanService;

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
    @ResponseBody
    public Result searchApplication(
            @RequestParam("c_min") int cMin
            , @RequestParam("c_max") int cMax
            , @RequestParam("w_min") int wMin
            , @RequestParam("w_max") int wMax
            , @RequestParam("b_min") int bMin
            , @RequestParam("b_max") int bMax
            , @RequestParam("id") Long id) {
        // 查询考生信息
        Candidate candidate = candidateService.getCandidateById(id);
        if (candidate == null) {
            return Result.error(CodeMsg.CANDIDATE_NOT_EXISTS);
        }
        List<String> kms = dealWithKms(candidate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String s = simpleDateFormat.format(new Date());
        String nf = (Integer.valueOf(s) - 1) + "";
        Map<String, Object> map = new HashMap<>(4);
        map.put("nf", nf);
        map.put("list", kms);
        map.put("min", cMin);
        map.put("max", cMax);
        // 查询冲志愿
        int cSchool = recruitStudentsPlanService.countSchool(map);
        int cMajor = recruitStudentsPlanService.countMajor(map);
        // 查询稳志愿
        map.put("min", wMin);
        map.put("max", wMax);
        int wSchool = recruitStudentsPlanService.countSchool(map);
        int wMajor = recruitStudentsPlanService.countMajor(map);
        // 查询保志愿
        map.put("min", bMin);
        map.put("max", bMax);
        int bSchool = recruitStudentsPlanService.countSchool(map);
        int bMajor = recruitStudentsPlanService.countMajor(map);
        map.clear();
        map.put("cSchool", cSchool);
        map.put("cMajor", cMajor);
        map.put("wSchool", wSchool);
        map.put("wMajor", wMajor);
        map.put("bSchool", bSchool);
        map.put("bMajor", bMajor);
        // 根据过去三年的名次平均值进行计算
        return Result.success(map);
    }

    @RequestMapping("/searchDetail")
    public String toDetail(@RequestParam("min") int min, @RequestParam("max") int max, @RequestParam("id") Long id, Model model) {
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("id", id);
        return "/lead/detail";
    }

    /**
     * 查询冲稳保 中一种
     *
     * @param min 最小名次
     * @param max 最大名次
     * @param id  考生编号
     * @return 专业详情
     */
    @RequestMapping("/detail")
    public Result searchDetail(@RequestParam("min") int min, @RequestParam("max") int max, @RequestParam("id") Long id) {
        // 查询考生信息
        Candidate candidate = candidateService.getCandidateById(id);
        if (candidate == null) {
            return Result.error(CodeMsg.CANDIDATE_NOT_EXISTS);
        }
        List<String> kms = dealWithKms(candidate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String s = simpleDateFormat.format(new Date());
        String nf = (Integer.valueOf(s) - 1) + "";
        Map<String, Object> map = new HashMap<>(4);
        map.put("nf", nf);
        map.put("list", kms);
        map.put("min", min);
        map.put("max", max);
        //显示符合条件的志愿
        List<RecruitStudentsPlanVo> recruitStudentsPlanList = recruitStudentsPlanService.listRecruitStudentsPlan(map);
        return Result.success(recruitStudentsPlanList);
    }

    private List<String> dealWithKms(Candidate candidate) {
        List<String> kms = new ArrayList<>();
        //生物
        if (candidate.getBiology() == 0) {
            kms.add("06");
        }
        //化学
        if (candidate.getChemistry() == 0) {
            kms.add("05");
        }
        //历史
        if (candidate.getHistory() == 0) {
            kms.add("08");
        }
        //物理
        if (candidate.getPhysics() == 0) {
            kms.add("04");
        }
        //思想政治
        if (candidate.getPolitics() == 0) {
            kms.add("07");
        }
        //技术
        if (candidate.getTechnology() == 0) {
            kms.add("13");
        }
        //地理
        if (candidate.getGeography() == 0) {
            kms.add("09");
        }
        return kms;
    }
}