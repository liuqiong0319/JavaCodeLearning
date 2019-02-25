package com.vip.qa.autov.core.jsoncompare.filter;

import org.skyscreamer.jsonassert.JSONCompareMode;

import java.math.BigDecimal;

/**
 * Created by kevin02.zhou on 2017/12/5.
 */
public class NumberCompareFilter implements ValueCompareFilter {

    private BigDecimal deviations;

    public NumberCompareFilter(double acceptableDeviations) {
        this.deviations = new BigDecimal(acceptableDeviations).abs();
    }

    @Override
    public ValueCompareResult process(final String key, final Object expectedValue, final Object actualValue, final JSONCompareMode mode) {
        if (isNumber(actualValue) && isNumber(expectedValue)) {
            BigDecimal expectedNumber = transToBigDecimal(expectedValue);
            BigDecimal actualNumber = transToBigDecimal(actualValue);
            if (expectedNumber.subtract(actualNumber).abs().compareTo(deviations) <= 0) {
                return ValueCompareResult.PASSED;
            }
            else {
                return ValueCompareResult.FAILED;
            }
        }
        return ValueCompareResult.COMPARE;
    }

    private BigDecimal transToBigDecimal(final Object value){
        //处理百分数
        if(value instanceof String){
            String strValue = (String)value;
            if (strValue.endsWith("%")) {
                strValue = strValue.substring(0, strValue.length() - 1);
                return new BigDecimal(strValue).movePointLeft(2);
            }
        }
        return new BigDecimal(value.toString());
    }

    private boolean isNumber(final Object value) {
        boolean isNum = false;
        if (value instanceof Number) {
            isNum = true;
        }
        else if (value instanceof String) {
            String strValue = (String)value;
            if(strValue.endsWith("%")){
                strValue = strValue.substring(0, strValue.length()-1);
            }
            try {
                Double.parseDouble(strValue);
                isNum = true;
            }
            catch (Exception e) {
                //ignore
            }
        }
        return isNum;
    }

}
