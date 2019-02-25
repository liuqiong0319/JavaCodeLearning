package com.vip.qa.autov.mock.scripting;

import com.vip.qa.autov.core.common.MockSimpleWeakHashMap;
import com.vip.qa.autov.core.utils.Exceptions;
import com.vip.qa.autov.core.utils.JsonUtils;
import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.Script;
import groovy.util.XmlSlurper;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kevin02.zhou on 2017/12/1.
 */
public class MockScriptExecutor {

    private static Logger logger = LoggerFactory.getLogger(MockScriptExecutor.class);
    private static Pattern scriptBlockPattern = Pattern.compile("\\$\\{([\\s\\S]+?)}");
    //ScriptCache是全局的，MockClassLoader的Cache的只对当前loader有效
    private static final MockSimpleWeakHashMap<String, Class<Script>> scriptCache = new MockSimpleWeakHashMap<>();

    private MockClassLoader classLoader;

    public MockScriptExecutor(ClassLoader parentClassLoader) {
        this.classLoader = toMockClassLoader(parentClassLoader);
    }

    private Object execute(String scriptText, Binding binding) throws Exception {
        Class cachedScriptClass = compileScript(scriptText);
        Script scriptInstance = InvokerHelper.createScript(cachedScriptClass, binding);
        Object result = scriptInstance.run();
        return result;
    }

    /**
     *
     * @param scriptText 需要执行的groovy脚本
     * @param params 脚本参数，脚本中固定使用params引用
     * @param extraParamMap 脚本额外参数，脚本引用时，使用extraParamMap中的key值，将会得到其对应value
     * @return 脚本执行结果
     * @throws Exception
     */
    public Object execute(String scriptText, Object params, Map<String, Object> extraParamMap) throws Exception {
        Binding binding = createBinding(params, extraParamMap);
        return execute(scriptText, binding);
    }

    /**
     *
     * @param template 需要执行的模板，其中动态脚本部分需要用${}括起来
     * @param params 脚本参数，脚本中固定使用params引用
     * @param extraParamMap 脚本额外参数，脚本引用时，使用extraParamMap中的key值，将会得到其对应value
     * @return 求值后的模板结果
     */
    public String evalTemplate(String template, Object params, Map<String, Object> extraParamMap) {
        if (StringUtils.isBlank(template) || !template.contains("${")) {
            return template;
        }
        Matcher matcher = scriptBlockPattern.matcher(template);
        StringBuffer sb = new StringBuffer();

        Binding binding = createBinding(params, extraParamMap);
        try {
            while (matcher.find()) {
                String toEval = matcher.group(1);
                Object result = execute(toEval, binding);
                if (result != null) {
                    matcher.appendReplacement(sb, Matcher.quoteReplacement(result.toString()));
                }else{
                    matcher.appendReplacement(sb, Matcher.quoteReplacement("null"));
                }
            }
            matcher.appendTail(sb);
        } catch (Exception e) {
            Exceptions.checked(e);
        }
        return sb.toString();
    }

    public Class compileScript(String scriptText) {
        Class compiledScriptClass = scriptCache.get(scriptText);
        if (compiledScriptClass == null) {
            synchronized (scriptCache) {
                compiledScriptClass = scriptCache.get(scriptText);
                if (compiledScriptClass == null) {
                    compiledScriptClass = classLoader.parseClass(scriptText);
                    scriptCache.put(scriptText, compiledScriptClass);
                }
            }
        }
        return compiledScriptClass;
    }

    public Object execute(String scriptText) throws Exception {
        return execute(scriptText, null, null);
    }

    public Class<Script> getLoadedClass(String scriptText) {
        Class<Script> loadedClass = scriptCache.get(scriptText);
        if (loadedClass == null) {
            loadedClass = this.classLoader.getLoadedClass(scriptText);
        }
        return loadedClass;
    }

    public void removeCache() {
        this.scriptCache.clear();
        this.classLoader.clearCache();
    }

    public void removeCache(String scriptText) {
        this.scriptCache.remove(scriptText);
        this.classLoader.removeCache(scriptText);
    }

    public MockClassLoader getClassLoader() {
        return this.classLoader;
    }

    private MockClassLoader toMockClassLoader(ClassLoader classLoader) {
        if (classLoader instanceof MockClassLoader) {
            return (MockClassLoader) classLoader;
        }
        else {
            return new MockClassLoader(classLoader);
        }
    }

    private Binding createBinding(Object params, Map<String, Object> extraParamMap) {
        Binding binding = new Binding();
        if (extraParamMap != null) {
            for (Map.Entry<String, Object> entry : extraParamMap.entrySet()) {
                binding.setVariable(entry.getKey(), entry.getValue());
            }
        }
        binding.setVariable("params", params);
        binding.setVariable("logger", logger);
        binding.setVariable("toPrettyJson", toPrettyJsonClosure);
        binding.setVariable("toSimpleJson", toSimpleJsonClosure);
        binding.setVariable("parseJson", parseJsonTextClosure);
        binding.setVariable("parseXml", parseXmlClosure);
        return binding;
    }

    private Closure<String> toPrettyJsonClosure = new Closure<String>(this, this) {
        public String doCall(Object object) {
            return JsonUtils.toPrettyJson(object);
        }
    };

    private Closure<String> toSimpleJsonClosure = new Closure<String>(this, this) {
        public String doCall(Object object) {
            return JsonUtils.toSimpleJson(object);
        }
    };

    private Closure<String> parseJsonTextClosure = new Closure<String>(this, this) {
        public Object doCall(String object) {
            return JsonUtils.parseText(object);
        }
    };

    private Closure<String> parseXmlClosure = new Closure<String>(this, this) {
        public Object doCall(String object) throws ParserConfigurationException, SAXException, IOException {
            return new XmlSlurper().parseText(object);
        }
    };
}
