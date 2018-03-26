package com.normal;

import com.entity.Hxzy;
import com.util.normal.BigDecimalUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 潘根山
 * @create 2018-03-25 11:14
 * @since 1.0.0
 */
public class ExcelWrite {
    @Test
    public void test1() throws IOException {
        List<Hxzy> hxzyList = new ArrayList<>();
        Hxzy hxzy = new Hxzy();
        hxzy.setCandidateId(1L);
        hxzy.setMajorCode("1");
        hxzy.setMajorName("航天航空专业");
        hxzy.setReferenceIndex(new BigDecimal(0.99));
        hxzy.setSchoolCode("2");
        hxzy.setSchoolName("浙江大学");
        hxzy.setStatus("冲");
        for (int i = 0; i < 80; i++) {
            hxzyList.add(hxzy);
        }
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        String[] cellHead = {"学校", "学校代码", "专业", "专业代码", "参考指数", "志愿类型"};
        Cell cell;
        for (int i = 0, len = cellHead.length; i < len; i++) {
            cell = row.createCell(i);
            cell.setCellValue(cellHead[i]);
        }
        for (int j = 1, len = hxzyList.size(); j <= len; j++) {
            row = sheet.createRow(j);
            for (int i = 0, iLen = cellHead.length; i < iLen; i++) {
                cell = row.createCell(i);
                switch (i) {
                    case 0:
                        cell.setCellValue(hxzy.getSchoolName());
                        break;
                    case 1:
                        cell.setCellValue(hxzy.getSchoolCode());
                        break;
                    case 2:
                        cell.setCellValue(hxzy.getMajorName());
                        break;
                    case 3:
                        cell.setCellValue(hxzy.getMajorCode());
                        break;
                    case 4:
                        cell.setCellValue(BigDecimalUtil.convertBigDecimalToPercent(hxzy.getReferenceIndex()));
                        break;
                    case 5:
                        cell.setCellValue(hxzy.getStatus());
                        break;
                    default:
                        break;
                }
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream("D:/候选志愿.xls");
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();
        System.out.println("导出完毕");
    }
}
