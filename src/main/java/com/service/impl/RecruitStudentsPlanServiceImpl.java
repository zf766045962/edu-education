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
    public void uploadData(MultipartFile file, String nf) throws IOException, CustomException {
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
            for (int j = 0; j < 32; j++) {
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
                        recruitStudentsPlan.setSfsyl(v);
                        break;
                    case 7:
                        recruitStudentsPlan.setBxlxdm(CommonUtils.convertStringToInteger(v));
                        break;
                    case 8:
                        recruitStudentsPlan.setBxlxmc(v);
                        break;
                    case 9:
                        recruitStudentsPlan.setSsdm(CommonUtils.convertStringToInteger(v));
                        break;
                    case 10:
                        recruitStudentsPlan.setSsmc(v);
                        break;
                    case 11:
                        recruitStudentsPlan.setZgdm(CommonUtils.convertStringToInteger(v));
                        break;
                    case 12:
                        recruitStudentsPlan.setZgmc(v);
                        break;
                    case 13:
                        recruitStudentsPlan.setCcdm(CommonUtils.convertStringToInteger(v));
                        break;
                    case 14:
                        recruitStudentsPlan.setCcmc(v);
                        break;
                    case 15:
                        recruitStudentsPlan.setZydm(v);
                        break;
                    case 16:
                        recruitStudentsPlan.setSbdm2(v == null ? "" : v);
                        break;
                    case 17:
                        recruitStudentsPlan.setZymc(v);
                        break;
                    case 18:
                        recruitStudentsPlan.setXzdmxg(v);
                        break;
                    case 19:
                        recruitStudentsPlan.setXzdm(v);
                        break;
                    case 20:
                        recruitStudentsPlan.setXzmc(v);
                        break;
                    case 21:
                        recruitStudentsPlan.setSfbz(v);
                        break;
                    case 22:
                        recruitStudentsPlan.setKldm(v);
                        break;
                    case 23:
                        recruitStudentsPlan.setKsklmc(v);
                        break;
                    case 24:
                        recruitStudentsPlan.setPcdm(v);
                        break;
                    case 25:
                        recruitStudentsPlan.setPcmc(v);
                        break;
                    case 26:
                        recruitStudentsPlan.setKslxdm(v);
                        break;
                    case 27:
                        recruitStudentsPlan.setKslxmc(v);
                        break;
                    case 28:
                        if ("00".equals(v)) {
                            recruitStudentsPlan.setKm1("00");
                            recruitStudentsPlan.setKm2("00");
                            recruitStudentsPlan.setKm3("00");
                        } else {
                            String[] strs = v.split("\\|");
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
                    case 29:
                        recruitStudentsPlan.setXkkmyqzw(v);
                        break;
                    case 30:
                        recruitStudentsPlan.setZsjhs(CommonUtils.convertStringToInteger(v));
                        break;
                    case 31:
                        recruitStudentsPlan.setBz(v);
                        break;
                    default:
                        break;
                }
            }
            recruitStudentsPlan.setZydmNew(recruitStudentsPlan.getZydm() + "" + recruitStudentsPlan.getSbdm2());
            recruitStudentsPlan.setNf(nf);
            recruitStudentsPlan.setZszymc(recruitStudentsPlan.getZymc());
            recruitStudentsPlan.setZsjhsSy(recruitStudentsPlan.getZsjhs());
            recruitStudentsPlans.add(recruitStudentsPlan);
        }
        if (recruitStudentsPlans.size() > 0) {
            //清空招生计划库
            dealWithRecruitStudentsPlan(nf, recruitStudentsPlans);
        }
        if (inp != null) {
            inp.close();
        }
    }

    private void dealWithRecruitStudentsPlan(String nf, List<RecruitStudentsPlan> recruitStudentsPlans) {
        recruitStudentsPlanMapper.deleteRecruitStudentsPlan(nf);
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
        recruitStudentsPlans.clear();
        recruitStudentsPlans = null;
        temp = null;
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

    @Override
    public List<RecruitStudentsPlan> findZymc(String year) {
        return recruitStudentsPlanMapper.findZymc(year);
    }


    private void insertRecruitStudentsPlanTempBatch(List<RecruitStudentsPlan> recruitStudentsPlans) {
        int len = recruitStudentsPlans.size();
        if (len <= Constants.EXCEL_BATCH_SIZE) {
            recruitStudentsPlanMapper.insertRecruitStudentsPlanTempBatch(recruitStudentsPlans);
        }
        List<RecruitStudentsPlan> temp = new ArrayList<>(Constants.EXCEL_BATCH_SIZE);
        for (int i = 0; i < len; i++) {
            if (i > 0 && i % Constants.EXCEL_BATCH_SIZE == 0) {
                recruitStudentsPlanMapper.insertRecruitStudentsPlanTempBatch(temp);
                temp.clear();
            }
            temp.add(recruitStudentsPlans.get(i));
        }
        if (temp.size() > 0) {
            recruitStudentsPlanMapper.insertRecruitStudentsPlanTempBatch(temp);
        }
        temp.clear();
        recruitStudentsPlans.clear();
        recruitStudentsPlans = null;
        temp = null;
    }

    @Override
    public void splitRecruitStudentsPlan(String year) {
        List<RecruitStudentsPlan> list = recruitStudentsPlanMapper.findZymc(year);
        if (list.size() > 0) {
            List<RecruitStudentsPlan> tempList = new ArrayList<>();
            RecruitStudentsPlan rSp;
            for (RecruitStudentsPlan recruitStudentsPlan : list) {
                String bz = recruitStudentsPlan.getBz();
                bz = bz.substring(bz.indexOf("$") + 1, bz.lastIndexOf("$"));
                String[] strs = bz.split("、");
                for (String s : strs) {
                    rSp = new RecruitStudentsPlan();
                    setRecruitStudentsPlan(rSp, recruitStudentsPlan, s);
                    tempList.add(rSp);
                }
            }
            //插入到临时表
            insertRecruitStudentsPlanTempBatch(tempList);
            //根据备注删除数据
            recruitStudentsPlanMapper.deleteRecruitStudentsPlanByBz(year);
        }
        //将临时表中的数据放回真实表中
        recruitStudentsPlanMapper.insertTempToReal();
        //删除临时表数据
        recruitStudentsPlanMapper.deleteRecruitStudentsPlanTemp();
    }

    @Override
    public void uploadNewData(MultipartFile file) throws IOException, CustomException {
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
            for (int j = 0; j < 5; j++) {
                String v = AbstractExcelUtil.getCellByType2(row.getCell(j));
                switch (j) {
                    case 0:
                        recruitStudentsPlan.setYxdh(v);
                        break;
                    case 1:
                        recruitStudentsPlan.setYxdhmc(v);
                        break;
                    case 2:
                        recruitStudentsPlan.setZydm(v);
                    case 3:
                        recruitStudentsPlan.setZszymc(v);
                        break;
                    case 4:
                        recruitStudentsPlan.setZsjhsSy(CommonUtils.convertStringToInteger(v));
                        break;
                    default:
                        break;
                }
            }
            recruitStudentsPlan.setZydmNew(recruitStudentsPlan.getZydm());
            recruitStudentsPlans.add(recruitStudentsPlan);
        }
        if (recruitStudentsPlans.size() > 0) {
            recruitStudentsPlanMapper.deleteRecruitStudentsPlanTemp();
            insertRecruitStudentsPlanTempBatch(recruitStudentsPlans);
        }
        if (inp != null) {
            inp.close();
        }
    }

    @Override
    public void generateSyzsjhs(String year) {
        //根据临时表更新剩余招生计划数
        recruitStudentsPlanMapper.updateSyzsjhsByTemp(year);
        //将临时表中不存在的数据更新为0
        recruitStudentsPlanMapper.updateSyzsjhsToZero(year);
        //清除临时表数据
        recruitStudentsPlanMapper.deleteRecruitStudentsPlanTemp();
    }

    private void setRecruitStudentsPlan(RecruitStudentsPlan rSp, RecruitStudentsPlan old, String s) {
        rSp.setYxdm(old.getYxdm());
        rSp.setYxdh(old.getYxdh());
        rSp.setYxdhmc(old.getYxdhmc());
        rSp.setSzd(old.getSzd());
        rSp.setSf985(old.getSf985());
        rSp.setSf211(old.getSf211());
        rSp.setSfsyl(old.getSfsyl());
        rSp.setBxlxdm(old.getBxlxdm());
        rSp.setBxlxmc(old.getBxlxmc());
        rSp.setSsdm(old.getSsdm());
        rSp.setSsmc(old.getSsmc());
        rSp.setZgdm(old.getZgdm());
        rSp.setCcdm(old.getCcdm());
        rSp.setCcmc(old.getCcmc());
        rSp.setZydm(old.getZydm());
        rSp.setSbdm2(old.getSbdm2());
        rSp.setZymc(s);
        rSp.setXzdmxg(old.getXzdmxg());
        rSp.setXzdm(old.getXzdm());
        rSp.setXzmc(old.getXzmc());
        rSp.setSfbz(old.getSfbz());
        rSp.setKldm(old.getKldm());
        rSp.setKsklmc(old.getKsklmc());
        rSp.setPcdm(old.getPcdm());
        rSp.setPcmc(old.getPcmc());
        rSp.setKslxdm(old.getKslxdm());
        rSp.setKslxmc(old.getKslxmc());
        rSp.setXkkmyq(old.getXkkmyq());
        rSp.setXkkmyqzw(old.getXkkmyqzw());
        rSp.setZsjhs(old.getZsjhs());
        rSp.setBz(old.getBz());
        rSp.setKm1(old.getKm1());
        rSp.setKm2(old.getKm2());
        rSp.setKm3(old.getKm3());
        rSp.setZydmNew(old.getZydmNew());
        rSp.setZsjhsSy(old.getZsjhsSy());
        rSp.setZszymc(old.getZszymc());
        rSp.setNf(old.getNf());
    }
}