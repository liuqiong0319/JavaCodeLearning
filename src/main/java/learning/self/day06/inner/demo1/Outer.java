package java.learning.self.day06.inner.demo1;

public class Outer {
	private int num = 12;
	private static int num2 = 14;

	static class Inner3 {
		static void show3() {
			System.out.println("outer num3=" + num2);
		}
	}

	static class Inner2 {
		void show2() {
			System.out.println("outer num2=" + num2);
		}
	}

	class Inner {
		void show() {
			System.out.println("outer num=" + num);
		}

		// static void method(){}//非静态内部类中不允许定义静态成员。仅允许在非静态内部类中定义 静态常量 static
		// final。
		// 如果想要在内部类中定义静态成员，必须内部类也要被静态修饰。
		// static void method() {
		//
		// }
	}

	void method() {
		Inner in = new Inner();
		in.show();

	}

}
