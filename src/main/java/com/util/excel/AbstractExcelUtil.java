package com.util.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

/**
 * @author 84548
 */
public abstract class AbstractExcelUtil {
    /**
     * 验证是否是excel2003
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 验证是否是excel2007
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 根据文件名判断
     *
     * @param inputStream
     * @param fileName
     * @return
     * @throws IOException
     */
    public static Workbook getExcel(InputStream inputStream, String fileName) throws IOException {
        if (fileName == null) {
            return null;
        }
        if (isExcel2003(fileName)) {
            return new HSSFWorkbook(inputStream);
        }
        if (isExcel2007(fileName)) {
            return new XSSFWorkbook(inputStream);
        }
        return null;
    }

    /**
     * 判断Excel倒入数据类型，转换为数据库可识别的数据类型
     *
     * @param cell 单元格
     */
    public static String getCellByType(Cell cell) {
        if (null == cell) {
            return null;
        }
        CellType cellType = cell.getCellTypeEnum();
        if (cellType.equals(CellType.NUMERIC)) {
            DecimalFormat df = new DecimalFormat("0.0000");
            return String.valueOf(df.format(cell.getNumericCellValue()));
        }
        if (cellType.equals(CellType.STRING)) {
            return cell.getStringCellValue().trim();
        }
        if (cellType.equals(CellType.BOOLEAN)) {
            return cell.getBooleanCellValue() + "";
        }
        if (cellType.equals(CellType.FORMULA)) {
            try {
                DecimalFormat df = new DecimalFormat("0.0000");
                return String.valueOf(df.format(cell.getNumericCellValue()));
            } catch (IllegalStateException e) {
                return String.valueOf(cell.getRichStringCellValue());
            }
        }
        if (cellType.equals(CellType.BLANK)) {
            return "";
        }
        if (cellType.equals(CellType.ERROR)) {
            return null;
        }
        return null;
    }
}