package java.learning.self.day05;

public class StaticCode {

	static int x = 9;
	static {// 静态代码块，随着类的加载而执行，且仅执行一次。一般作为预加载.在静态变量显示初始化以后再执行。
		System.out.println("类预加载完成！--------" + x);
	}

	static void print() {
		System.out.println("��ɴ�ӡ����");
	}

}
