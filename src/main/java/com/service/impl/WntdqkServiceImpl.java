package com.service.impl;

import com.common.Constants;
import com.common.result.CodeMsg;
import com.dao.WntdqkMapper;
import com.entity.Wntdqk;
import com.service.WntdqkService;
import com.util.excel.AbstractExcelUtil;
import com.util.exception.CustomException;
import com.util.normal.CommonUtils;
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

/**
 * @author 潘根山
 * @create 2018-03-18 20:24
 * @since 1.0.0
 */
@Service
public class WntdqkServiceImpl implements WntdqkService {
    @Autowired
    private WntdqkMapper wntdqkMapper;

    @Override
    public void uploadData(MultipartFile file, String year) throws IOException, CustomException {
        InputStream inp = file.getInputStream();
        String fileName = file.getOriginalFilename();
        Workbook workbook = AbstractExcelUtil.getExcel(inp, fileName);
        if (workbook == null) {
            throw new CustomException(CodeMsg.FILE_DATA_EMPTY.getMsg());
        }
        Sheet sheet = workbook.getSheetAt(0);
        Row row;
        List<Wntdqk> wdtdqkList = new ArrayList<>();
        Wntdqk wntdqk;
        for (int i = 1, len = sheet.getLastRowNum(); i <= len; i++) {
            row = sheet.getRow(i);
            wntdqk = new Wntdqk();
            wntdqk.setNf(year);
            for (int j = 0; j < 10; j++) {
                String v = AbstractExcelUtil.getCellByType(row.getCell(j));
                switch (j) {
                    case 1:
                        wntdqk.setSchooleCode(v);
                        break;
                    case 2:
                        wntdqk.setSchoolName(v);
                        break;
                    case 3:
                        wntdqk.setZydm(v);
                        break;
                    case 4:
                        wntdqk.setZymc(v);
                        break;
                    case 6:
                        wntdqk.setZdf(CommonUtils.convertStringToInteger(v));
                        break;
                    case 7:
                        wntdqk.setTdmc(CommonUtils.convertStringToInteger(v));
                        break;
                    case 9:
                        wntdqk.setCkzs(v);
                        break;
                    default:
                        break;
                }
            }
            wdtdqkList.add(wntdqk);
        }
        if (wdtdqkList.size() > 0) {
            int len = wdtdqkList.size();
            if (len <= Constants.EXCEL_BATCH_SIZE) {
                wntdqkMapper.insertWntdqkBatch(wdtdqkList);
            }
            List<Wntdqk> temp = new ArrayList<>(Constants.EXCEL_BATCH_SIZE);
            for (int i = 0; i < len; i++) {
                if (i > 0 && i % Constants.EXCEL_BATCH_SIZE == 0) {
                    wntdqkMapper.insertWntdqkBatch(temp);
                    temp.clear();
                }
                temp.add(wdtdqkList.get(i));
            }
            if (temp.size() > 0) {
                wntdqkMapper.insertWntdqkBatch(temp);
            }
            temp.clear();
        }
        if (inp != null) {
            inp.close();
        }
    }
}