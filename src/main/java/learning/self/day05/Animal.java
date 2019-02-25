package java.learning.self.day05;

public class Animal {
	private String name;
	private int age;
	int num = 6;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Animal() {// 父类的构造函数
		System.out.println("父类的构造函数");
	}

	public void show() {
		System.out.println("父类的show方法运行了");
	}

}
