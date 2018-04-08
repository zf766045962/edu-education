package com.controller.lead;

import com.common.result.Result;
import com.entity.Candidate;
import com.entity.Hxzy;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.CandidateService;
import com.service.HxzyService;
import com.util.excel.DownLoad;
import com.util.normal.BigDecimalUtil;
import com.vo.HxzyVo;
import com.vo.LoginUser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 候选之志愿控制器
 *
 * @author 潘根山
 * @create 2018-03-22 22:12
 * @since 1.0.0
 */
@Controller
@RequestMapping("/hxzy")
public class HxzyController {
    @Autowired
    private HxzyService hxzyService;
    @Autowired
    private CandidateService candidateService;

    @RequestMapping("/searchHx/{id}")
    public String searchHx(@PathVariable("id") Long candidateId, Model model) {
        model.addAttribute("candidateId", candidateId);
        return "/lead/hxzy";
    }

    /**
     * 查询考生候选志愿
     *
     * @param hxzy        candidateId 考生ID
     * @param currentPage 当前页
     * @param pageSize    每页显示的条数
     * @return 候选志愿集合
     */
    @GetMapping("/search")
    @ResponseBody
    public Result listHzxy(
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
            , @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
            , Hxzy hxzy) {
        PageHelper.startPage(currentPage, pageSize);
        List<HxzyVo> hxzyList = hxzyService.listHxzyByCandidateId(hxzy);
        PageInfo<HxzyVo> pageInfo = new PageInfo<>(hxzyList);
        List<HxzyVo> list = pageInfo.getList();
        HxzyVo hxzyVo;
        for (int i = 0, len = list.size(); i < len; i++) {
            hxzyVo = list.get(i);
            hxzyVo.setReferenceIndexName(BigDecimalUtil.convertBigDecimalToPercent(hxzyVo.getReferenceIndex()));
        }
        return Result.page(list, pageInfo.getTotal());
    }

    /**
     * 清空考生候选志愿
     *
     * @param candidateId 考生ID
     * @return result
     */
    @PostMapping("/clear")
    @ResponseBody
    public Result clearAll(@RequestParam("candidateId") Long candidateId, LoginUser user) {
        hxzyService.deleteAllByCandidateId(candidateId);
        return Result.success(true);
    }

    /**
     * 删除一个或多个候选志愿
     *
     * @param ids 候选志愿集合 id1,id2
     * @return result
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam("ids") String ids, LoginUser user) {
        String[] strs = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String s : strs) {
            idList.add(Long.parseLong(s));
        }
        hxzyService.deleteByIds(idList);
        return Result.success(true);
    }

    /**
     * 收藏志愿
     *
     * @param hxzy 候选志愿对象
     * @return result
     */
    @PostMapping("add")
    @ResponseBody
    public Result addHxzy(Hxzy hxzy) {
        hxzy.setGmtCreate(new Date());
        hxzy.setGmtModified(new Date());
        hxzyService.insertSelective(hxzy);
        return Result.success(true);
    }

    /**
     * 取消志愿
     *
     * @param id         考生id
     * @param majorCode  专业代码
     * @param schoolCode 学校代码
     * @return result
     */
    @PostMapping("cancel")
    @ResponseBody
    public Result cancelHxzy(@RequestParam("id") long id
            , @RequestParam("schoolCode") String schoolCode
            , @RequestParam("majorCode") String majorCode) {
        hxzyService.deleteByCandidateIdAndSchoolCodeAndMajorCode(id, schoolCode, majorCode);
        return Result.success(true);
    }

    @GetMapping("/export")
    public void export(@RequestParam("candidateId") Long candidateId, HttpServletResponse response) throws IOException {
        Hxzy hxzy = new Hxzy();
        hxzy.setCandidateId(candidateId);
        List<HxzyVo> hxzyList = hxzyService.listHxzyByCandidateId(hxzy);
        Candidate candidate = candidateService.getCandidateById(candidateId);
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        String[] cellHead = {"学校", "学校代码", "专业", "专业代码", "参考指数", "志愿类型"};
        Cell cell;
        for (int i = 0, len = cellHead.length; i < len; i++) {
            cell = row.createCell(i);
            cell.setCellValue(cellHead[i]);
        }
        HxzyVo hxzyVo;
        int k = 1;
        for (HxzyVo aHxzyList : hxzyList) {
            row = sheet.createRow(k++);
            hxzyVo = aHxzyList;
            for (int i = 0, iLen = cellHead.length; i < iLen; i++) {
                cell = row.createCell(i);
                switch (i) {
                    case 0:
                        cell.setCellValue(hxzyVo.getSchoolName());
                        break;
                    case 1:
                        cell.setCellValue(hxzyVo.getSchoolCode());
                        break;
                    case 2:
                        cell.setCellValue(hxzyVo.getMajorName());
                        break;
                    case 3:
                        cell.setCellValue(hxzyVo.getMajorCode());
                        break;
                    case 4:
                        cell.setCellValue(BigDecimalUtil.convertBigDecimalToPercent(hxzyVo.getReferenceIndex()));
                        break;
                    case 5:
                        cell.setCellValue(hxzyVo.getStatusName());
                        break;
                    default:
                        break;
                }
            }
        }
        String path = candidate.getName() + "的候选志愿" + System.currentTimeMillis() + ".xls";
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();
        DownLoad.download(path, response);
    }
}