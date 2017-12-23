package learning.self.day05;

public class Dog extends Animal {
	// 父类默认有super()构造函数
	public Dog() {// 子类中的所有构造函数的第一行都有一句隐式的super();
		super();
		System.out.println("子类的构造函数");
	}

	public Dog(int x) {
		// 当父类中有显示的空参构造函数时，子类中的所有的构造函数都会调用默认的空参构造函数，而不是调用父类对应含有参数的构造函数
		// super();
		System.out.println("子类的构造函数");
	}

	// 子类不能直接访问父类的私有内容，但是可以继承，可以使用get/set方法进行访问
	public double weight;
	int num = 9;

	public void show() {// 父类和子类定义了相同的方法，子类对象调用时，直接运行子类的方法即重写Overwride
		System.out.println("父类：" + super.num);// super单独存在无异议
		System.out.println("子类：" + this.num);
		super.show();// 如果子类中还需要用到父类的部分功能，可以通过super调用
	}

	public void Animal() {
		System.out.println("覆盖(override)：继承了父类的同名无參函数");
	}
}
