package java.learning.self.day06.inner.demo4;

//当描述事务时，事务的内部还有事务，这个内部的事务还在访问外部事务中的内容，就可以将这个事务定义为内部类。
public class Outer {
	public int num = 10;

	// 成员内部类
	public class Inner {
		public void show() {
			System.out.println("innner class。。。。" + num);
		}
	}

	public void method() {
		Inner inner = new Inner();
		// Outer.Inner inner=new Outer.Inner(); //Inner本身的写法
		inner.show();
	}
}
