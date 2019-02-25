package com.vip.qa.autov.core.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.DumperOptions.ScalarStyle;
import org.yaml.snakeyaml.Yaml;

public class YamlUtils {

	private static Yaml yaml;

	static {
		DumperOptions options = new DumperOptions();
		options.setDefaultScalarStyle(ScalarStyle.PLAIN);
		options.setDefaultFlowStyle(FlowStyle.AUTO);
		options.setExplicitStart(false);
		options.setExplicitEnd(false);
		options.setPrettyFlow(true);
		yaml = new Yaml(options);
	}

	public static void dump(Object obj, File file) throws IOException {
		yaml.dump(obj, new FileWriter(file));
	}

	public static <T> void dumpList(List<T> objs, File file) throws IOException {
		yaml.dumpAll(objs.iterator(), new FileWriter(file));
	}

	public static <T> String toYamlList(List<T> objs) {
		StringWriter sw = new StringWriter();
		yaml.dumpAll(objs.iterator(), sw);
		return sw.toString();
	}

	public static String toYaml(Object obj) {
		StringWriter sw = new StringWriter();
		yaml.dump(obj, sw);
		return sw.toString();
	}

	public static Object fromYaml(String yamlString) {
		return yaml.load(yamlString);
	}

	public static <T> T fromYaml(String yamlString, Class<T> clazz) {
		return yaml.loadAs(yamlString, clazz);
	}

	public static List<Object> fromYamlList(String yamlString) {
		List<Object> list = new ArrayList<>();
		Iterable<Object> iterable = yaml.loadAll(yamlString);
		for (Object data : iterable) {
			list.add(data);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> fromYamlList(String yamlString, Class<T> clazz) {
		List<T> list = new ArrayList<>();
		Iterable<Object> iterable = yaml.loadAll(yamlString);
		for (Object data : iterable) {
			list.add((T) data);
		}
		return list;
	}

	public static <T> List<T> fromYamlList(File file, Class<T> clazz) {
		String yamlString = null;
		try {
			yamlString = FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			Exceptions.checked(e);
		}
		return fromYamlList(yamlString, clazz);
	}

	public static List<Object> fromYamlList(File file) {
		String yamlString = null;
		try {
			yamlString = FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			Exceptions.checked(e);
		}
		return fromYamlList(yamlString);
	}

	public static Object fromYaml(File file) {
		String yaml = null;
		try {
			yaml = FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			Exceptions.checked(e);
		}
		return fromYaml(yaml);
	}

	public static <T> T fromYaml(File file, Class<T> clazz) {
		String yaml = null;
		try {
			yaml = FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			Exceptions.checked(e);
		}

		return fromYaml(yaml, clazz);
	}
}
