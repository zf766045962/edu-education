package com.normal;

import com.SpringBaseTest;
import com.common.Constants;
import com.dao.RecruitStudentsPlanMapper;
import com.dao.WntdqkMapper;
import com.entity.RecruitStudentsPlan;
import com.entity.Wntdqk;
import com.util.excel.AbstractExcelUtil;
import com.util.normal.CommonUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 潘根山
 * @create 2018-03-11 19:41
 * @since 1.0.0
 */
public class ExcelRead extends SpringBaseTest {
    @Autowired
    private RecruitStudentsPlanMapper recruitStudentsPlanMapper;
    @Autowired
    private WntdqkMapper wntdqkMapper;

    @Test
    public void testPlan() throws IOException {
        String file = "D:\\work\\高考志愿填报辅助系统\\2017年普高计划库.xls";
        FileInputStream fileInput = new FileInputStream(file);
        Workbook workbook;
        if (AbstractExcelUtil.isExcel2003(file)) {
            workbook = new HSSFWorkbook(fileInput);
        } else if (AbstractExcelUtil.isExcel2007(file)) {
            workbook = new XSSFWorkbook(fileInput);
        } else {
            workbook = null;
        }
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row;
            List<RecruitStudentsPlan> recruitStudentsPlans = new ArrayList<>();
            RecruitStudentsPlan recruitStudentsPlan;
            for (int i = 1, len = sheet.getLastRowNum(); i <= len; i++) {
                row = sheet.getRow(i);
                recruitStudentsPlan = new RecruitStudentsPlan();
                for (int j = 0; j < 31; j++) {
                    String v = AbstractExcelUtil.getCellByType(row.getCell(j));
                    switch (j) {
                        case 0:
                            recruitStudentsPlan.setYxdm(v);
                            break;
                        case 1:
                            recruitStudentsPlan.setYxdh(v);
                            break;
                        case 2:
                            recruitStudentsPlan.setYxdhmc(v);
                            break;
                        case 3:
                            recruitStudentsPlan.setSzd(v);
                            break;
                        case 4:
                            recruitStudentsPlan.setSf985(CommonUtils.convertStringToInteger(v));
                            break;
                        case 5:
                            recruitStudentsPlan.setSf211(CommonUtils.convertStringToInteger(v));
                            break;
                        case 6:
                            recruitStudentsPlan.setBxlxdm(CommonUtils.convertStringToInteger(v));
                            break;
                        case 7:
                            recruitStudentsPlan.setBxlxmc(v);
                            break;
                        case 8:
                            recruitStudentsPlan.setSsdm(CommonUtils.convertStringToInteger(v));
                            break;
                        case 9:
                            recruitStudentsPlan.setSsmc(v);
                            break;
                        case 10:
                            recruitStudentsPlan.setZgdm(CommonUtils.convertStringToInteger(v));
                            break;
                        case 11:
                            recruitStudentsPlan.setZgmc(v);
                            break;
                        case 12:
                            recruitStudentsPlan.setCcdm(CommonUtils.convertStringToInteger(v));
                            break;
                        case 13:
                            recruitStudentsPlan.setCcmc(v);
                            break;
                        case 14:
                            recruitStudentsPlan.setZydm(v);
                            break;
                        case 15:
                            recruitStudentsPlan.setSbdm2(v);
                            break;
                        case 16:
                            recruitStudentsPlan.setZymc(v);
                            break;
                        case 17:
                            recruitStudentsPlan.setXzdmxg(v);
                            break;
                        case 18:
                            recruitStudentsPlan.setXzdm(v);
                            break;
                        case 19:
                            recruitStudentsPlan.setXzmc(v);
                            break;
                        case 20:
                            recruitStudentsPlan.setSfbz(v);
                            break;
                        case 21:
                            recruitStudentsPlan.setKldm(v);
                            break;
                        case 22:
                            recruitStudentsPlan.setKsklmc(v);
                            break;
                        case 23:
                            recruitStudentsPlan.setPcdm(v);
                            break;
                        case 24:
                            recruitStudentsPlan.setPcmc(v);
                            break;
                        case 25:
                            recruitStudentsPlan.setKslxdm(v);
                            break;
                        case 26:
                            recruitStudentsPlan.setKslxmc(v);
                            break;
                        case 27:
                            recruitStudentsPlan.setXkkmyq(v);
                            break;
                        case 28:
                            recruitStudentsPlan.setXkkmyqzw(v);
                            break;
                        case 29:
                            recruitStudentsPlan.setZsjhs(CommonUtils.convertStringToInteger(v));
                            break;
                        case 30:
                            recruitStudentsPlan.setBz(v);
                            break;
                    }
                }
                recruitStudentsPlans.add(recruitStudentsPlan);
            }
//            for (RecruitStudentsPlan re:recruitStudentsPlans){
//                System.out.println(re.toString());
//            }
            if (recruitStudentsPlans.size() > 0) {
                int len = recruitStudentsPlans.size();
                if (len <= Constants.EXCEL_BATCH_SIZE) {
                    recruitStudentsPlanMapper.insertRecruitStudentsPlanBatch(recruitStudentsPlans);
                }
                List<RecruitStudentsPlan> temp = new ArrayList<>(Constants.EXCEL_BATCH_SIZE);
                for (int i = 0; i < len; i++) {
                    if (i > 0 && i % Constants.EXCEL_BATCH_SIZE == 0) {
                        recruitStudentsPlanMapper.insertRecruitStudentsPlanBatch(temp);
                        temp.clear();
                    }
                    temp.add(recruitStudentsPlans.get(i));
                }
                if (temp.size() > 0) {
                    recruitStudentsPlanMapper.insertRecruitStudentsPlanBatch(temp);
                }
            }
        }

        fileInput.close();
    }

    @Test
    public void test2() {
        String str = "50";
        String[] strs = str.split("\\.");
        System.out.println(Integer.parseInt(strs[0]));
    }

    @Test
    public void testOldPlan() throws IOException {
        String file = "D:\\work\\高考志愿填报辅助系统\\2014-2017投档数据.xlsx";
        FileInputStream fileInput = new FileInputStream(file);
        Workbook workbook = AbstractExcelUtil.getExcel(fileInput, file);
        Assert.assertNotNull(workbook);
        Sheet sheet = workbook.getSheetAt(1);
        Row row;
        List<Wntdqk> list = new ArrayList<>();
        Wntdqk wntdqk;
        for (int i = 1, len = sheet.getLastRowNum(); i <= len; i++) {
            row = sheet.getRow(i);
            wntdqk = new Wntdqk();
            for (int j = 0, jLen = row.getLastCellNum(); j < jLen; j++) {
                String v = AbstractExcelUtil.getCellByType(row.getCell(j));
                switch (j) {
                    case 0:
                        wntdqk.setNf(v == null ? "" : v.split("\\.")[0]);
                        break;
                    case 2:
                        wntdqk.setPc(CommonUtils.convertStringToInteger(v));
                        break;
                    case 4:
                        wntdqk.setSchooleCode(v);
                        break;
                    case 5:
                        wntdqk.setSchoolName(v);
                        break;
                    case 7:
                        wntdqk.setZymc(v);
                        break;
                    case 8:
                        wntdqk.setKl(v);
                        break;
                    case 10:
                        wntdqk.setPjf(CommonUtils.convertStringToInteger(v));
                        break;
                    case 11:
                        wntdqk.setZdf(CommonUtils.convertStringToInteger(v));
                        break;
                    case 12:
                        wntdqk.setTdmc(CommonUtils.convertStringToInteger(v));
                        break;
                    case 14:
                        wntdqk.setTdrs(CommonUtils.convertStringToInteger(v));
                    case 15:
                        wntdqk.setCkzs(new BigDecimal(v));
                        break;
                    default:
                        break;
                }
            }
            list.add(wntdqk);
        }
        if (list.size() > 0) {
            int len = list.size();
            if (len <= Constants.EXCEL_BATCH_SIZE) {
                wntdqkMapper.insertWntdqkBatch(list);
            }
            List<Wntdqk> temp = new ArrayList<>(Constants.EXCEL_BATCH_SIZE);
            for (int i = 0; i < len; i++) {
                if (i > 0 && i % Constants.EXCEL_BATCH_SIZE == 0) {
                    wntdqkMapper.insertWntdqkBatch(temp);
                    temp.clear();
                }
                temp.add(list.get(i));
            }
            if (temp.size() > 0) {
                wntdqkMapper.insertWntdqkBatch(temp);
            }
            temp.clear();
        }
    }
}
