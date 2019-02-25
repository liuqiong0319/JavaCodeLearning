package com.vip.qa.autov.core.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarUtils {

	public static URL[] getClassLoaderURLs() {
		URLClassLoader cl = (URLClassLoader) Thread.currentThread().getContextClassLoader();
		return cl.getURLs();
	}

	public static void extendJar(String dirPath, String jarName) {
		File dir = new File(dirPath);
		File file = new File(dir, jarName);
		try {
			FileUtils.forceMkdir(dir);
			extendJar(file);
		} catch (IOException e) {
		}
	}

	public static void extendJar(URLClassLoader cl, File... files) throws IOException {
		if (files == null) {
			return;
		}
		try {
			for (File file : files) {
				URL fileUrl = file.toURI().toURL();
				boolean readyAdded = false;
				for (URL currentUrl : cl.getURLs()) {
					if (currentUrl.equals(fileUrl)) {
						readyAdded = true;
						break;
					}
				}
				if (!readyAdded) {
					ReflectionUtils.invokeMethod(cl, "addURL", new Class[] { URL.class }, fileUrl);
				}
			}
		} catch (Exception e) {
			Exceptions.checked(e);
		}
	}

	public static String getJarVersion(Class<?> clazz) {
		String path = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
		String version = null;
		if (path.endsWith(".jar")) {
			path = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
			String[] parts = path.split("-");
			int length = parts.length;
			if (length >= 1) {
				version = parts[length - 1];
				if (length >= 2 && equalsAnyIgnoreCase(version, "SNAPSHOT", "RELEASE", "FINAL")) {
					version = parts[length - 2] + "-" + version;
				}
			}
		}
		return version;
	}

    private static boolean equalsAnyIgnoreCase(final CharSequence string, final CharSequence...searchStrings) {
        if (ArrayUtils.isNotEmpty(searchStrings)) {
            for (CharSequence next : searchStrings) {
                if (StringUtils.equalsIgnoreCase(string, next)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getJarName(Class<?> clazz) {
		String path = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
		if (path.endsWith(".jar")) {
			String version = path.substring(path.lastIndexOf("/") + 1);
			return version;
		}
		return null;
	}

	public static String getJarName(String className) {
		Class<?> clazz;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			return null;
		}

		String path = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
		if (path.endsWith(".jar")) {
			String version = path.substring(path.lastIndexOf("/") + 1);
			return version;
		}
		return null;
	}

	public static void extendJar(URLClassLoader cl, URL... urls) throws IOException {
		if (urls == null) {
			return;
		}
		try {
			for (URL url : urls) {
				ReflectionUtils.invokeMethod(cl, "addURL", new Class[] { URL.class }, url);
			}
		} catch (Exception e) {
			Exceptions.checked(e);
		}
	}

	/**
	 * 获取某包下（包括该包的所有子包）所有类
	 * 
	 * @param packageName
	 *            包名
	 * @return 类的完整名称
	 */
	public static List<String> getClassName(String packageName) {
		return getClassName(packageName, true);
	}

	/**
	 * 获取某包下所有类
	 * 
	 * @param packageName
	 *            包名
	 * @param childPackage
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	public static List<String> getClassName(String packageName, boolean childPackage) {
		List<String> fileNames = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(".", "/");
		URL url = loader.getResource(packagePath);
		if (url != null) {
			String type = url.getProtocol();
			if (type.equals("file")) {
				fileNames = getClassNameByFile(url.getPath(), null, childPackage);
			} else if (type.equals("jar")) {
				fileNames = getClassNameByJar(url.getPath(), packagePath, childPackage);
			}
		} else {
			fileNames = getClassNameByJars(((URLClassLoader) loader).getURLs(), packagePath, childPackage);
		}
		return fileNames;
	}

	/**
	 * 从项目文件获取某包下所有类
	 * 
	 * @param filePath
	 *            文件路径
	 * @param className
	 *            类名集合
	 * @param childPackage
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
		List<String> myClassName = new ArrayList<String>();
		File file = new File(filePath);
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				if (childPackage) {
					myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));
				}
			} else {
				String childFilePath = childFile.getPath();
				if (childFilePath.endsWith(".class")) {
					childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9,
							childFilePath.lastIndexOf("."));
					childFilePath = childFilePath.replace("\\", ".");
					myClassName.add(childFilePath);
				}
			}
		}

		return myClassName;
	}

	/**
	 * 从jar获取某包下所有类
	 * 
	 * @param jarPath
	 *            jar文件路径
	 * @param childPackage
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	public static List<String> getClassNameByJar(String jarPath, String basePackagePath, boolean childPackage) {
		List<String> myClassName = new ArrayList<String>();
		String jarFilePath = jarPath;
		String packagePath = basePackagePath;
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(jarFilePath);
			Enumeration<JarEntry> entrys = jarFile.entries();
			while (entrys.hasMoreElements()) {
				JarEntry jarEntry = entrys.nextElement();
				String entryName = jarEntry.getName();
				if (entryName.endsWith(".class")) {
					if (childPackage) {
						if (entryName.startsWith(packagePath)) {
							entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
							myClassName.add(entryName);
						}
					} else {
						int index = entryName.lastIndexOf("/");
						String myPackagePath;
						if (index != -1) {
							myPackagePath = entryName.substring(0, index);
						} else {
							myPackagePath = entryName;
						}
						if (myPackagePath.equals(packagePath)) {
							entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
							myClassName.add(entryName);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (IOException e) {
				}
			}
		}
		return myClassName;
	}

	public static String getPackageNameByJar(String jarPath, String simplePackageName) {
		String jarFilePath = jarPath;
		String pName = null;
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(jarFilePath);
			Enumeration<JarEntry> entrys = jarFile.entries();
			while (entrys.hasMoreElements()) {
				JarEntry jarEntry = entrys.nextElement();
				String entryName = jarEntry.getName();

				if (entryName.endsWith(simplePackageName + "/")) {
					pName = entryName.substring(0, entryName.length() - 1).replace("/", ".");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (IOException e) {
				}
			}
		}
		return pName;
	}

	/**
	 * 从所有jar中搜索该包，并获取该包下所有类
	 * 
	 * @param urls
	 *            URL集合
	 * @param packagePath
	 *            包路径
	 * @param childPackage
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	private static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) {
		List<String> myClassName = new ArrayList<String>();
		if (urls != null) {
			for (int i = 0; i < urls.length; i++) {
				URL url = urls[i];
				String urlPath = url.getPath();
				// 不必搜索classes文件夹
				if (urlPath.endsWith("classes/")) {
					continue;
				}
				String jarPath = urlPath + "!/" + packagePath;
				myClassName.addAll(getClassNameByJar(jarPath, packagePath, childPackage));
			}
		}
		return myClassName;
	}

	public static void extendJar(File... files) throws IOException {
		URLClassLoader cl = (URLClassLoader) Thread.currentThread().getContextClassLoader();
		extendJar(cl, files);
	}

	public static Set<String> scanClassNames(File file) {
		Set<String> serviceNames = new HashSet<>();
		List<String> names = getClassNameByJar(file.getAbsolutePath(), "", true);
		for (String name : names) {
			serviceNames.add(name);
		}

		return serviceNames;
	}

	public static void main(String[] args) throws Exception {
		URLClassLoader systemCl = (URLClassLoader) ClassLoader.getSystemClassLoader();
		URLClassLoader cl = new URLClassLoader(new URL[] {}, systemCl);
		extendJar(cl, new File("../autov-cache/target/autov-cache-master-SNAPSHOT.jar"));
		System.out.println(cl.loadClass("com.vip.qa.autov.cache.BBB").getDeclaredFields().length);
		Thread.currentThread().setContextClassLoader(cl);
		cl.close();
		System.out.println(cl.loadClass("com.vip.qa.autov.cache.BBB").getDeclaredFields().length);
	}

	public static void closeJars(URLClassLoader cl) throws Exception {
		try {
			Class clazz = URLClassLoader.class;
			Field ucp = clazz.getDeclaredField("ucp");
			ucp.setAccessible(true);
			Object sunMiscURLClassPath = ucp.get(cl);
			Field loaders = sunMiscURLClassPath.getClass().getDeclaredField("loaders");
			loaders.setAccessible(true);
			Object collection = loaders.get(sunMiscURLClassPath);
			for (Object sunMiscURLClassPathJarLoader : ((Collection) collection).toArray()) {
				try {
					Field loader = sunMiscURLClassPathJarLoader.getClass().getDeclaredField("jar");
					loader.setAccessible(true);
					Object jarFile = loader.get(sunMiscURLClassPathJarLoader);
					JarFile jarF = (JarFile) jarFile;
					((Collection) collection).remove(sunMiscURLClassPathJarLoader);
					jarF.close();
				} catch (Throwable t) {
					// if we got this far, this is probably not a JAR
					// loader so
					// skip it
				}
			}
			// }
		} catch (Throwable t) {
			// probably not a SUN VM
		}
		return;
	}

}
