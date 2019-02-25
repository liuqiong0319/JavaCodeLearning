package com.vip.qa.autov.core.jsoncompare.filter;

import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kevin02.zhou on 2017/9/22.
 */
public class IgnoreByKeyCompareFilter implements ValueCompareFilter {

    private List<String> excludesProperties;

    public IgnoreByKeyCompareFilter(List<String> excludesProperties) {
        this.excludesProperties = excludesProperties;
    }

    public IgnoreByKeyCompareFilter(String... excludesProperties) {
        this.excludesProperties = Arrays.asList(excludesProperties);
    }

    @Override
    public ValueCompareResult process(final String key, final Object expectedValue, final Object actualValue, final JSONCompareMode mode) {
        boolean shouldExclude = false;
        for (String excludesProperty : excludesProperties) {
            if (key.endsWith(excludesProperty)) {
                shouldExclude = true;
                break;
            }
        }

        if (shouldExclude) {
            return ValueCompareResult.PASSED;
        }
        else {
            return ValueCompareResult.COMPARE;
        }
    }
}
