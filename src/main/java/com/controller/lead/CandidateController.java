package com.controller.lead;

import com.common.Constants;
import com.common.result.CodeMsg;
import com.common.result.Result;
import com.entity.Candidate;
import com.entity.CandidateNum;
import com.entity.Cwbbl;
import com.entity.Province;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.*;
import com.util.excel.DownLoad;
import com.util.normal.BigDecimalUtil;
import com.util.normal.CommonUtils;
import com.vo.LoginUser;
import com.vo.RecruitStudentsPlanVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
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
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CwbblService cwbblService;
    @Autowired
    private CandidateNumService candidateNumService;

    @RequestMapping("/")
    public String toCandidate() {
        return "lead/candidate";
    }

    @RequestMapping("/search/{id}")
    public String toSearch(@PathVariable("id") Long id, Model model) {
        Candidate candidate = candidateService.getCandidateById(id);
        List<Province> provinces = provinceService.listProvince();
        Cwbbl cwbbl = cwbblService.getCwbbl();
        Integer ranking = candidate.getRanking();
        CandidateNum candidateNum = candidateNumService.getCandidateNum();
        model.addAttribute("candidate", candidate);
        model.addAttribute("provinces", provinces);
        model.addAttribute("cwbbl", cwbbl);
        model.addAttribute("cMin", (int) (ranking * (1 - cwbbl.getChong())));
        model.addAttribute("wMax", (int) (ranking * (1 + cwbbl.getWen())));
        model.addAttribute("bMax", (int) (ranking * (1 + cwbbl.getWen() + cwbbl.getBao())));
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        model.addAttribute("zs", (Double.valueOf(decimalFormat.format(((double) ranking / candidateNum.getNum() * 100))) + "%"));
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
        PageHelper.startPage(currentPage, pageSize);
        if (!loginUser.getLoginUserName().contains(Constants.USER_PREFIX)) {
            candidate.setConsultantId(loginUser.getLoginId());
        }
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
     * @param cMin         冲位次最小值
     * @param cMax         冲位次最大值
     * @param wMin         稳位次最小值
     * @param wMax         稳位次最大值
     * @param bMin         保位次最小值
     * @param bMax         保位次最大值
     * @param id           考生id
     * @param provinceCode 省市名称
     * @param majorCode    专业名称
     * @param schoolCode   院校名称
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
            , @RequestParam("id") Long id
            , String provinceCode
            , String majorCode
            , String schoolCode) {
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
        if (CommonUtils.isNotEmpty(provinceCode)) {
            String[] provinceCodes = provinceCode.split(",");
            if (provinceCodes.length > 0) {
                map.put("provinceCodes", provinceCodes);
            }
        }
        if (CommonUtils.isNotEmpty(majorCode)) {
            String[] majorCodes = majorCode.split(",");
            if (majorCodes.length > 0) {
                map.put("majorCodes", majorCodes);
            }
        }
        if (CommonUtils.isNotEmpty(schoolCode)) {
            String[] schoolCodes = schoolCode.split(",");
            if (schoolCodes.length > 0) {
                map.put("schoolCodes", schoolCodes);
            }
        }
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
    public String toDetail(
            @RequestParam("min") int min
            , @RequestParam("max") int max
            , @RequestParam("id") Long id
            , String provinceCode
            , String majorCodeSession
            , String schoolCodeSession
            , String type
            , Model model
            , HttpSession httpSession) {
        Candidate candidate = candidateService.getCandidateById(id);
        CandidateNum candidateNum = candidateNumService.getCandidateNum();
        model.addAttribute("candidate", candidate);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("id", id);
        model.addAttribute("provinceCode", provinceCode);
        model.addAttribute("majorCode", httpSession.getAttribute(majorCodeSession));
        model.addAttribute("schoolCode", httpSession.getAttribute(schoolCodeSession));
        model.addAttribute("type", type);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        model.addAttribute("zs", (Double.valueOf(decimalFormat.format(((double) candidate.getRanking() / candidateNum.getNum() * 100))) + "%"));
        httpSession.removeAttribute(majorCodeSession);
        httpSession.removeAttribute(schoolCodeSession);
        return "/lead/detail";
    }

    @PostMapping("/sendMsg")
    @ResponseBody
    public Result sendMsg(String schoolCode, String majorCode, @RequestParam("id") Long id, LoginUser user, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        String schoolCodeSessionId = "schoolCode" + user.getLoginUserName() + id + System.currentTimeMillis();
        String majorCodeSessionId = "majorCode" + user.getLoginUserName() + id + System.currentTimeMillis();
        httpSession.setAttribute(schoolCodeSessionId, schoolCode);
        httpSession.setAttribute(majorCodeSessionId, majorCode);
        Map<String, Object> map = new HashMap<>();
        map.put("schoolCodeSession", schoolCodeSessionId);
        map.put("majorCodeSession", majorCodeSessionId);
        return Result.success(map);
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
    @ResponseBody
    public Result searchDetail(@RequestParam("min") int min
            , @RequestParam("max") int max
            , @RequestParam("id") Long id
            , String provinceCode
            , String majorCode
            , @RequestParam("pageSize") int pageSize
            , @RequestParam("currentPage") int currentPage
            , String schoolName
            , String majorName
            , String schoolCode) {
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
        map.put("schoolName", schoolName);
        map.put("majorName", majorName);
        map.put("candidateId", id);
        if (CommonUtils.isNotEmpty(provinceCode)) {
            String[] provinceCodes = provinceCode.split(",");
            if (provinceCodes.length > 0) {
                map.put("provinceCodes", provinceCodes);
            }
        }
        if (CommonUtils.isNotEmpty(majorCode)) {
            String[] majorCodes = majorCode.split(",");
            if (majorCodes.length > 0) {
                map.put("majorCodes", majorCodes);
            }
        }
        if (CommonUtils.isNotEmpty(schoolCode)) {
            String[] schoolCodes = schoolCode.split(",");
            if (schoolCodes.length > 0) {
                map.put("schoolCodes", schoolCodes);
            }
        }
        //显示符合条件的志愿
        PageHelper.startPage(currentPage, pageSize);
        List<RecruitStudentsPlanVo> recruitStudentsPlanList = recruitStudentsPlanService.listRecruitStudentsPlan(map);
        PageInfo<RecruitStudentsPlanVo> pageInfo = new PageInfo<>(recruitStudentsPlanList);
        List<RecruitStudentsPlanVo> r = pageInfo.getList();
        RecruitStudentsPlanVo recruitStudentsPlanVo;
        for (int i = 0, len = r.size(); i < len; i++) {
            recruitStudentsPlanVo = r.get(i);
            recruitStudentsPlanVo.setCkzsName(BigDecimalUtil.convertBigDecimalToPercent(recruitStudentsPlanVo.getCkzs()));
        }
        return Result.page(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 考生导出
     *
     * @param loginUser loginUser
     * @param response  response
     * @throws IOException IOException
     */
    @GetMapping("/exportCandidate")
    public void exportCandidate(LoginUser loginUser, HttpServletResponse response) throws IOException {
        List<Candidate> candidates = candidateService.listCandidateByCondition(new Candidate());
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        String[] cellHead = {"姓名", "总分", "排名", "联系电话", "创建用户"};
        Cell cell;
        for (int i = 0, len = cellHead.length; i < len; i++) {
            cell = row.createCell(i);
            cell.setCellValue(cellHead[i]);
        }
        int k = 1;
        for (Candidate candidate : candidates) {
            row = sheet.createRow(k++);
            for (int i = 0, iLen = cellHead.length; i < iLen; i++) {
                cell = row.createCell(i);
                switch (i) {
                    case 0:
                        cell.setCellValue(candidate.getName());
                        break;
                    case 1:
                        cell.setCellValue(candidate.getTotalScore());
                        break;
                    case 2:
                        cell.setCellValue(candidate.getRanking());
                        break;
                    case 3:
                        cell.setCellValue(candidate.getContactNumber());
                        break;
                    case 4:
                        cell.setCellValue(candidate.getConsultantName());
                        break;
                    default:
                        break;
                }
            }
        }
        String path = "考生信息" + System.currentTimeMillis() + ".xls";
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();
        DownLoad.download(path, response);
    }

    private List<String> dealWithKms(Candidate candidate) {
        List<String> kms = new ArrayList<>();
        //生物
        if (candidate.getBiology() == 1) {
            kms.add("06");
        }
        //化学
        if (candidate.getChemistry() == 1) {
            kms.add("05");
        }
        //历史
        if (candidate.getHistory() == 1) {
            kms.add("08");
        }
        //物理
        if (candidate.getPhysics() == 1) {
            kms.add("04");
        }
        //思想政治
        if (candidate.getPolitics() == 1) {
            kms.add("07");
        }
        //技术
        if (candidate.getTechnology() == 1) {
            kms.add("13");
        }
        //地理
        if (candidate.getGeography() == 1) {
            kms.add("09");
        }
        return kms;
    }
}