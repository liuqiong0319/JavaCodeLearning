package com.vip.qa.autov.core.api;

import com.vip.qa.autov.core.AutoVConsts;
import com.vip.qa.autov.core.utils.Exceptions;
import com.vip.qa.autov.core.utils.FreeMarkerUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.*;

public class ApiRepoGenerator {

	private static String configStr;

	private static ResourceLoader loader = new DefaultResourceLoader();

	static {
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = loader.getResource("classpath:template/apiRepo.ftl").getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String valueString = null;
			while ((valueString = br.readLine()) != null) {
				sb.append(valueString).append("\n");
			}
			configStr = sb.toString();
		} catch (IOException e) {
			Exceptions.checked(e);
		} finally {
			IOUtils.closeQuietly(br);
			IOUtils.closeQuietly(is);
		}
	}

	/**
	 * 把接口模板导出为api repository接口类
	 * 
	 * @param domainOrService
	 *            template_api下域名或者服务名
	 * @param destinationClass
	 *            目标包名.接口名
	 */
	public static void generate(String domainOrService, String destinationClass) {
		File dir = new File(AutoVConsts.TEMPLATE_API_DIR + File.separator + domainOrService);
		if (!dir.exists()) {
			return;
		}

		Collection<File> files = FileUtils.listFiles(dir, null, true);
		Map context = new HashMap();
		List<Map<String, String>> settings = new ArrayList<>();
		if (files != null) {
			context.put("infos", settings);
			if (!destinationClass.contains(".")) {
				return;
			}
			String className = StringUtils.substringAfterLast(destinationClass, ".");
			context.put("className", className);
			String packageName = StringUtils.substringBeforeLast(destinationClass, ".");
			context.put("packageName", packageName);
			boolean isOsp = domainOrService.endsWith("Service") || domainOrService.endsWith("Api");
			if (isOsp) {
				context.put("responserType", "com.vip.qa.autov.osp.response.OspResponser");
			} else {
				context.put("responserType", "com.vip.qa.autov.http.response.HttpResponser");
			}
			for (File file : files) {
				Map<String, String> setting = parseSetting(dir, file, isOsp);
				settings.add(setting);
			}
			String classFilePath = destinationClass.replace(".", "/") + ".java";
			File classFile = new File("src/main/java" + File.separator + classFilePath);
			writeToFile(context, classFile);
		}
	}

	private static void writeToFile(Map context, File file) {
		try {
			String content = FreeMarkerUtils.parse(configStr, context);
			if (file.exists()) {
				file = new File(file.getParentFile(), file.getName() + "_tmp");
			}
			FileUtils.writeStringToFile(file, content, "UTF-8");
			System.out.println("File " + file + " created");
		} catch (Exception e) {
			System.err.println("Failed to create class file: " + file + " error msg:" + e.getMessage());

		}
	}

	private static Map<String, String> parseSetting(File dir, File file, boolean isOsp) {
		Map<String, String> map = new HashMap<>();
		String fileName = file.getName();
		map.put("method", fileName);
		String fileNameSuffix = StringUtils.substringAfter(file.getPath(), dir.getPath() + File.separator);
		boolean isAddApiSetting = !fileNameSuffix.equals(file.getName());
		if (isAddApiSetting) {
			map.put("name", fileNameSuffix.replace("\\", "/"));
		}
		String returnType = "HttpResponser";
		if (isOsp) {
			returnType = "OspResponser";
		}
		map.put("returnType", returnType);
		return map;
	}

}
