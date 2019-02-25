package com.vip.qa.autov.core.utils;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Use {@link com.vip.qa.autov.mock.scripting.MockScriptExecutor} instead
 */
@Deprecated
public class GroovyEvalUtils {

	private static Map<String, Class<Script>> sourceCache = new WeakHashMap<String, Class<Script>>();

	private static Logger logger = LoggerFactory.getLogger(GroovyEvalUtils.class);

	private static Pattern scriptBlockPattern = Pattern.compile("\\$\\{([\\s\\S]+?)}");

	public static String eval(String input, Map<String, ?> objectMap) {
		if (StringUtils.isBlank(input) || !input.contains("${")) {
			return input;
		}
		Matcher matcher = scriptBlockPattern.matcher(input);
		StringBuffer sb = new StringBuffer();

		Binding binding = new Binding();
		if (objectMap != null) {
			for (Entry<String, ?> entry : objectMap.entrySet()) {
				binding.setVariable(entry.getKey(), entry.getValue());
			}
		}
		try {
			while (matcher.find()) {
				String toEval = matcher.group(1);
				Class<Script> cachedScriptClass = compile(toEval);
				Script scriptInstance = cachedScriptClass.newInstance();
				scriptInstance.setBinding(binding);
				Object result = scriptInstance.run();
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

	public static Object evalNoPattern(String input, Map<String, ?> objectMap, Binding binding) {
		Class<Script> cachedScriptClass = compile(input);
		Object result = null;
		try {
			Script scriptInstance = cachedScriptClass.newInstance();
			if (binding == null) {
				binding = new Binding();
			}
			if (objectMap != null) {
				for (Entry<String, ?> entry : objectMap.entrySet()) {
					binding.setVariable(entry.getKey(), entry.getValue());
				}
			}
			scriptInstance.setBinding(binding);
			result = scriptInstance.run();
		} catch (Exception e) {
			logger.error("string before evaluation groovy script is:" + input);
			Exceptions.unchecked(e);
		}
		return result;
	}

	public static Object evalNoPattern(String input, Map<String, ?> objectMap) {
		return evalNoPattern(input, objectMap, null);
	}

	public static String eval(String input) {
		return eval(input, null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
	public static Class<Script> compile(String scriptText) {
		String key = scriptText;
		Class<Script> compiledScriptClass = sourceCache.get(key);
		if (compiledScriptClass == null) {
			synchronized (sourceCache) {
				if (compiledScriptClass == null) {
					GroovyClassLoader groovyClassLoader = new GroovyClassLoader(GroovyEvalUtils.class.getClassLoader());
					Class newScript = groovyClassLoader.parseClass(scriptText);
					sourceCache.put(key, newScript);
					return newScript;
				}
			}
		}
		return compiledScriptClass;
	}


	public static Script newScriptInstance(String scriptText) {
		if (StringUtils.isBlank(scriptText)) {
			return null;
		}
		Script script = null;
		Class<Script> scriptClass = compile(scriptText);
		try {
			script = scriptClass.newInstance();
		}
		catch (Exception e) {
			Exceptions.checked(e);
		}
		return script;
	}
}
