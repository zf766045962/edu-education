package com.util.normal;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_CEILING;

/**
 * BigDecimal 公共方法
 *
 * @author 潘根山
 * @create 2018-03-02 14:35
 * @since 1.0.0
 */
public class BigDecimalUtil {
    /**
     * 判断bigDecimal 是否为0
     *
     * @param bigDecimal 比较值
     * @return 是否为0
     */
    public static boolean checkZero(BigDecimal bigDecimal) {
        return bigDecimal != null && bigDecimal.compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * 计算同比增幅
     * (nowData-oldData)/oldData*100
     *
     * @param oldData 去年数据
     * @param nowData 当年数据
     * @return 同比增幅
     */
    public static BigDecimal CalculationYearOnYearGrowth(BigDecimal oldData, BigDecimal nowData) {
        return nowData.subtract(oldData).divide(oldData, ROUND_CEILING).multiply(new BigDecimal(100));
    }

    /**
     * 将String 转化为BigDecimal
     *
     * @param str 源数据
     * @return bigDecimal
     */
    public static BigDecimal convertStringToBigDecimal(String str) {
        if (CommonUtils.isEmpty(str)) {
            return new BigDecimal(0.00);
        }
        return BigDecimal.valueOf(Double.parseDouble(str));
    }

    public static String convertBigDecimalToPercent(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return "0.00%";
        }
        return String.valueOf(bigDecimal.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_EVEN)) + "%";
    }
}