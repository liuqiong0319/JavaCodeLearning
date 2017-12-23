package learning.self.day06.inner.demo1;

import learning.self.day06.inner.demo1.Outer.Inner2;
import learning.self.day06.inner.demo1.Outer.Inner3;

public class InnerClassDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Outer ou = new Outer();
		ou.method();
		// 直接访问Outer中的Inner内部类的非静态成员。
		// 创建内部类的对象。内部类作为成员，应该先有外部类对象，再有内部类对象。
		// 不多见：因为更多的时候，内部类已经被封装到了外部类中，不直接对外提供。
		Outer.Inner in = new Outer().new Inner();
		in.show();
		// 对静态内部类中的非静态成员进行调用。
		// 因为内部类是静态，所以不需要创建Outer的对象。直接创建内部类对象。
		Outer.Inner2 in2 = new Inner2();
		in2.show2();

		Inner3.show3();
		Outer2 ou2 = new Outer2();
		ou2.method();
	}

}
