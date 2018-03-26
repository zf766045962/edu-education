package com.service.impl;

import com.common.Constants;
import com.common.result.CodeMsg;
import com.dao.RecruitStudentsPlanMapper;
import com.entity.RecruitStudentsPlan;
import com.service.RecruitStudentsPlanService;
import com.util.excel.AbstractExcelUtil;
import com.util.exception.CustomException;
import com.util.normal.CommonUtils;
import com.vo.RecruitStudentsPlanVo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 潘根山
 * @create 2018-03-12 19:45
 * @since 1.0.0
 */
@Service
public class RecruitStudentsPlanServiceImpl implements RecruitStudentsPlanService {
    @Autowired
    private RecruitStudentsPlanMapper recruitStudentsPlanMapper;

    @Override
    public void uploadData(MultipartFile file) throws IOException, CustomException {
        InputStream inp = file.getInputStream();
        String fileName = file.getOriginalFilename();
        Workbook workbook = AbstractExcelUtil.getExcel(inp, fileName);
        if (workbook == null) {
            throw new CustomException(CodeMsg.FILE_DATA_EMPTY.getMsg());
        }
        Sheet sheet = workbook.getSheetAt(0);
        Row row;
        List<RecruitStudentsPlan> recruitStudentsPlans = new ArrayList<>();
        RecruitStudentsPlan recruitStudentsPlan;
        for (int i = 1, len = sheet.getLastRowNum(); i <= len; i++) {
            row = sheet.getRow(i);
            recruitStudentsPlan = new RecruitStudentsPlan();
            for (int j = 0; j < 31; j++) {
                String v = AbstractExcelUtil.getCellByType2(row.getCell(j));
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
                        if ("00".equals(v)) {
                            recruitStudentsPlan.setKm1("00");
                            recruitStudentsPlan.setKm2("00");
                            recruitStudentsPlan.setKm3("00");
                        } else {
                            String[] strs = v.split("\\+");
                            if (strs.length == 3) {
                                recruitStudentsPlan.setKm1(strs[0].trim());
                                recruitStudentsPlan.setKm2(strs[1].trim());
                                recruitStudentsPlan.setKm3(strs[2].trim());
                            } else if (strs.length == 2) {
                                recruitStudentsPlan.setKm1(strs[0].trim());
                                recruitStudentsPlan.setKm2(strs[1].trim());
                                recruitStudentsPlan.setKm3("no");
                            } else {
                                recruitStudentsPlan.setKm1(strs[0].trim());
                                recruitStudentsPlan.setKm2("no");
                                recruitStudentsPlan.setKm3("no");
                            }
                        }
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
                    default:
                        break;
                }
            }
            recruitStudentsPlan.setZydmNew(recruitStudentsPlan.getZydm() + "" + recruitStudentsPlan.getSbdm2());
            recruitStudentsPlans.add(recruitStudentsPlan);
        }
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
            temp.clear();
        }
        if (inp != null) {
            inp.close();
        }
    }

    @Override
    public int countSchool(Map<String, Object> map) {
        return recruitStudentsPlanMapper.countSchool(map);
    }

    @Override
    public int countMajor(Map<String, Object> map) {
        return recruitStudentsPlanMapper.countMajor(map);
    }

    @Override
    public List<RecruitStudentsPlanVo> listRecruitStudentsPlan(Map<String, Object> map) {
        return recruitStudentsPlanMapper.listRecruitStudentsPlan(map);
    }
}