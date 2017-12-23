package learning.self.day05;

//懒汉模式：一开始直接指向null
public class SingleMode1 {
	private static SingleMode1 sm = null;

	private SingleMode1() {

	}

	public static SingleMode1 getInstance() {
		if (sm == null)
			return sm = new SingleMode1();
		return sm;
	}
}
