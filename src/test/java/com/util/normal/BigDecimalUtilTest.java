package com.util.normal;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BigDecimalUtilTest {

    @Test
    public void convertBigDecimalToPercent() {
        System.out.println(BigDecimalUtil.convertBigDecimalToPercent(new BigDecimal(0.0022)));
    }
}