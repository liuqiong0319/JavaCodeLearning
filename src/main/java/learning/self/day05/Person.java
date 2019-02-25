package java.learning.self.day05;

public class Person {

	private String name;
	private int age;
	private boolean sex;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age >= 0 && age <= 130)
			this.age = age;
		else
			throw new RuntimeException("年龄输入有误");
	}

	public static void speak() {
		System.out.println("这是一个静态类");
	}

	// 构造函数
	public Person(int age, boolean sex) {
		this.age = age;
		this.sex = sex;
	}

	public boolean ageEqual(Person pp) {
		// if (pp.age == this.age)
		// return true;
		// else
		// return false;
		return pp.age == this.age;
	}
}
