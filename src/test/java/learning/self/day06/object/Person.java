package learning.self.day06.object;

public class Person {
	private int age;

	Person(int age) {
		this.age = age;
	}

	public boolean compare(Person p) {
		return this.age == p.age;
	}

	// 重写equals方法
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Person))
			return false;
		Person p = (Person) obj;
		return this.age == p.age;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + "]";
	}

}
