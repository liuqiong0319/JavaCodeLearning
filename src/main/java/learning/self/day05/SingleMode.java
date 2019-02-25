package java.learning.self.day05;

//单例模式:分为懒汉模式和饿汉模式
//饿汉模式：一开始就new对象
public class SingleMode {
	// 2.创建一个本类对象
	private static SingleMode sm = new SingleMode();

	// 1.将构造函数私有化
	private SingleMode() {

	}

	// 3.创建对外提供调用的功能，为了使功能可控，故需要额外添加getInstance方法
	public static SingleMode getInstance() {
		return sm;
	}
}
