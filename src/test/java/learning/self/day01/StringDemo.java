package learning.self.day01;

import java.util.Arrays;

/**
 * String 对象创建后则不能被修改，是不可变的，所谓的修改其实是创建了新的对象，所指向的内存空间不同 Created by joan.liu on
 * 2017/8/30.
 */
public class StringDemo {
	public static void stringAddress() {
		String s1 = "hello";
		String s4 = "hello";
		String s2 = new String();
		String s3 = new String("hello");
		String s5 = new String("hello");
		System.out.println(s1 == s4);
		System.out.println(s1 == s3);
		System.out.println(s3 == s5);
	}

	public static void stringOperat() {
		String str = "   I am hERe haha唯品会！   ";
		System.out.println(str.length());
		System.out.println(str.indexOf('h'));
		System.out.println(str.indexOf("am"));
		System.out.println(str.lastIndexOf('e'));
		System.out.println(str.lastIndexOf("好"));
		System.out.println(str.substring(5));
		System.out.println(str.substring(5, 8));
		System.out.println(str.trim());// 返回了去除前后空格的字符串
		System.out.println(str.equals("I am here 唯品会！"));
		System.out.println(str.toUpperCase());
		System.out.println(str.toLowerCase());
		System.out.println(str.charAt(6));
		String[] strings = str.split(" ");// 按空格把字符串拆分成一个数组。
		System.out.println(Arrays.toString(strings));// Arrays.toString(数组名)将数组输出
		byte[] array = str.getBytes();
		System.out.println(Arrays.toString(array));
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'h') {
				count++;
			}
		}
		System.out.println(count);
	}

	// 功能：将一个由英文字母组成的字符串转换成指定格式---从右边开始每三个字母用逗号分隔的形式。
	public static void stringBuild() {
		StringBuilder str = new StringBuilder();
		str.append("yes");
		str.append("i am here");
		str.insert(3, ",");
		System.out.println(str);
		StringBuilder str2 = new StringBuilder();
		str2.append("jaewkjldfxmopzdm");
		int count = 0;
		for (int i = str2.length() - 1; i >= 0; i--) {
			count++;
			if (count % 3 == 0) {
				str2.insert(i, ",");
			}
		}
		System.out.println(str2.toString());
	}

	/*
	 * 基本类型转换为字符串有三种方法：
	 * 
	 * 1. 使用包装类的 toString() 方法
	 * 
	 * 2. 使用String类的 valueOf() 方法
	 * 
	 * 3. 用一个空字符串加上基本类型，得到的就是基本类型数据对应的字符串
	 */
	public static void toStringTest() {
		double score = 99.2;
		System.out.println(Double.toString(score));
		System.out.println(String.valueOf(score));
		System.out.println(score + "");
		/*
		 * 将字符串转换成基本类型有两种方法：
		 * 
		 * 1. 调用包装类的 parseXxx 静态方法
		 * 
		 * 2. 调用包装类的 valueOf() 方法转换为基本类型的包装类，会自动拆箱
		 */
		String str = "16";
		int d = Integer.parseInt(str);
		int e = Integer.valueOf(str);

		double f = Double.parseDouble(str);
		double g = Double.valueOf(str);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);
		System.out.println(g);
	}

	public static void main(String[] args) {
		// stringAddress();
		// stringOperat();
		// stringBuild();
		toStringTest();
	}
}
