package learning.self.day06.inner.demo1;

public class Outer2 {
	private int num = 12;

	class Inner {
		int num = 13;

		void show() {
			int num = 17;
			System.out.println("num=" + num);
			System.out.println("num=" + this.num);
			System.out.println("num=" + Outer2.this.num);
		}
	}

	void method() {
		new Inner().show();
	}

}
