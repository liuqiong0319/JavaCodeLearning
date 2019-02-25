package com.vip.qa.autov.core.testcase;

import org.apache.commons.collections4.CollectionUtils;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.TestRunner;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MethodGroupMappingHolder {

    private static volatile boolean inited = false;

    private static XmlSuite xmlSuite;

    private static List<ITestNGMethod> allTestngMethods;

    private static Map<Method, Set<String>> methodGroupsMapping = new HashMap<>();


    /**
     * 根据method获取method所属的test的incluedGroups
     * 这里test是在testng.xml中配置的test，可能存在多个
     *
     * @param method
     * @return
     */
    public static Set<String> getGroupsByMethod(Method method) {
        if (!inited) {
            initMethodGroupMapping();
        }
        return methodGroupsMapping.get(method);
    }

    /**
     * 构建method与includeGroups的映射关系
     * 这里的test是指testng.xml中配置的test，可能有多个
     * 一个测试方法可能属于多个test，那样的话也会运行多次
     * test->includeGroups
     * method->test
     */
    private static void initMethodGroupMapping() {
        synchronized (MethodGroupMappingHolder.class) {
            if (inited) {
                return;
            }
            Map<String, Set<String>> testnameGroupMapping = new HashMap<>();
            List<XmlTest> xmlTests = xmlSuite.getTests();
            for (XmlTest xmlTest : xmlTests) {
                String testname = xmlTest.getName();
                List<String> includeGroups = xmlTest.getIncludedGroups();
                if (CollectionUtils.isNotEmpty(includeGroups)) {
                    testnameGroupMapping.put(testname, new HashSet<String>(includeGroups));
                }
            }

            methodGroupsMapping.clear();
            for (ITestNGMethod tMethod : allTestngMethods) {
                Method method = tMethod.getConstructorOrMethod().getMethod();
                String testname = tMethod.getXmlTest().getName();
                Set<String> groups = testnameGroupMapping.get(testname);
                if (CollectionUtils.isEmpty(groups)) {
                    continue;
                }

                if (methodGroupsMapping.containsKey(method)) {
                    methodGroupsMapping.get(method).addAll(groups);
                } else {
                    methodGroupsMapping.put(method, groups);
                }
            }
            inited = true;
        }
    }

    public static void init(ITestContext context) {
        MethodGroupMappingHolder.xmlSuite = (XmlSuite) ((TestRunner) context).getTest().getSuite().clone();
        allTestngMethods = context.getSuite().getAllMethods();
        inited = false;
    }
}
