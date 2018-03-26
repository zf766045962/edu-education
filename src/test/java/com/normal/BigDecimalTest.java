package com.normal;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author 潘根山
 * @create 2018-03-25 12:05
 * @since 1.0.0
 */
public class BigDecimalTest {
    @Test
    public void test1() {
        BigDecimal bigDecimal = new BigDecimal(0.99);
        BigDecimal b = bigDecimal.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_CEILING);
        System.out.println(String.valueOf(b));
    }
}
