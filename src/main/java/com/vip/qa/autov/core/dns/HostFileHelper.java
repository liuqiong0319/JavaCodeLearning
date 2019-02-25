package com.vip.qa.autov.core.dns;

import com.google.common.collect.Lists;
import com.vip.qa.autov.core.config.ConfigLoader;
import com.vip.qa.autov.core.utils.Exceptions;
import com.vip.qa.autov.core.utils.ReflectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.spi.nameservice.NameService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 解析项目的hosts文件
 * 
 * @author alex.ma
 *
 */
public class HostFileHelper {

	private static Logger logger = LoggerFactory.getLogger(HostFileHelper.class);

	private static Map<String, String> hostCache = new HashMap<>();

	private static volatile boolean isInitialized = false;

	static {
		String hostStr = ConfigLoader.getInstance().getHosts();
		if (StringUtils.isNotBlank(hostStr)) {
			List<String> hostList = parseHostToList(hostStr);
			parseHostToMap(hostList);
		}
		String sysHosts = getFromSystemHosts();
		if (sysHosts != null) {
			List<String> hostList = parseHostToList(sysHosts);
			parseHostToMap(hostList);
		}
	}

	/**
	 * 动态设置dns, 动态注入dns provider
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings({ "restriction", "unchecked" })
	public static synchronized void initDns() {
		if (isInitialized) {
			return;
		}

		addHost("localhost", "127.0.0.1");

		try {
			addHost(InetAddress.getLocalHost().getHostName(), "127.0.0.1");
		} catch (UnknownHostException e1) {
			logger.error(e1.getMessage());
		}
		Map<String, String> hostPairs = getHosts();
		for (Entry<String, String> entry : hostPairs.entrySet()) {
			String key = entry.getKey();
			addHost(key, entry.getValue());
		}

		List<NameService> nameServices = null;
		try {
			nameServices = (List<NameService>) ReflectionUtils.getStaticFieldValue(InetAddress.class,
					"nameServices");
		} catch (Exception e) {
			Exceptions.checked(e);
		}
		if (nameServices != null) {
			//仅仅添加Dns解析服务到解析链中，并作为优先，保留原来的解析服务
			nameServices.add(0, new LocalManagedDnsProxy());
		}
		logger.info("DNS initialized, content is {}", hostCache.toString());
		isInitialized = true;
	}

	public static void addHost(String host, String ip) {
		hostCache.put(host, ip);
		NameStore.getInstance().put(host, ip);
	}

	// 本地调试禁止使用本地hosts，必须配hosts文件
	private static String getFromSystemHosts() {
		// String os = System.getProperty("os.name");
		String hostsPath = "/etc/hosts";
		// if (os.toLowerCase().startsWith("win")) {
		// hostsPath = "C:\\Windows\\System32\\drivers\\etc\\hosts";
		// }
		File hostsFile = new File(hostsPath);
		if (hostsFile.exists()) {
			try {
				return FileUtils.readFileToString(hostsFile, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Map<String, String> getHosts() {
		return hostCache;
	}

	public static String get(String key) {
		return hostCache.get(key);
	}

	private static void parseHostToMap(List<String> hostStr) {
		for (String proxy : hostStr) {
			String[] pair = proxy.split("\\s+");
			if (pair.length > 1) {
				addHost(pair[1].trim(), pair[0].trim());
			}
		}
	}

	public static String toHostsString() {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String> entry : hostCache.entrySet()) {
			sb.append(entry.getValue()).append(" ").append(entry.getKey()).append(System.lineSeparator());
		}
		return sb.toString();
	}

	public static List<String> parseHostToList(String hostStr) {
		List<String> hostList = Lists.newArrayList();
		BufferedReader br = new BufferedReader(new StringReader(hostStr));
		try {
			String line = br.readLine();
			while (line != null) {
				line = line.trim();
				if (!line.startsWith("#") && !line.equals("")) {
					line = StringUtils.substringBefore(line, "#").trim();
					hostList.add(line);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
		} finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}
		return hostList;
	}
}
