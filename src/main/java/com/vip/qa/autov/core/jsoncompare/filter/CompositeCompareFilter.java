package com.vip.qa.autov.core.jsoncompare.filter;

import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * Created by kevin02.zhou on 2017/12/5.
 */
public class CompositeCompareFilter implements ValueCompareFilter {

    private ValueCompareFilter[] filters;

    public CompositeCompareFilter(ValueCompareFilter... filters) {
        this.filters = filters != null ? filters : new ValueCompareFilter[] {};
    }

    @Override
    public ValueCompareResult process(final String key, final Object expectedValue, final Object actualValue, final JSONCompareMode mode) {
        for (ValueCompareFilter filter : this.filters) {
            ValueCompareResult result = filter.process(key, expectedValue, actualValue, mode);
            if (result != null && !ValueCompareResult.COMPARE.equals(result)) {
                return result;
            }
        }
        return ValueCompareResult.COMPARE;
    }
}
