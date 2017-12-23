package learning.self.day01;

/**
 * Java 为每个基本数据类型都提供了一个包装类，这样我们就可以像操作对象那样来操作基本数据类型。 包装类主要提供了两大类方法： int
 * 默认值为0；Integer默认值为null；
 * <p>
 * 1. 将本类型和其他基本类型进行转换的方法
 * <p>
 * 2. 将字符串和本类型及包装类互相转换的方法 Created by joan.liu on 2017/8/30.
 */
public class PackClassDemo {

	public static void packClass() {
		int score = 34;
		// 装箱：把基本类型转换成包装类，使其具有对象的性质，又可分为手动装箱和自动装箱
		Integer score1 = new Integer(score);// 手动装箱
		Integer score2 = new Integer("86");
		Integer score4 = score;// 自动装箱
		// 和装箱相反，把包装类对象转换成基本类型的值，又可分为手动拆箱和自动拆箱
		int score6 = score2.intValue();// 手动拆箱
		int score7 = score2;// 自动拆箱
		byte score3 = score2.byteValue();
		String str = "halo";
		float score5 = score2.floatValue();
		System.out.println(score1);
		System.out.println(score2);
		System.out.println(score3);
		System.out.println(score4);
	}

	public static void main(String[] args) {
		packClass();
	}

}
