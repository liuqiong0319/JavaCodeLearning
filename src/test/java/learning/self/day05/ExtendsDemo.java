package learning.self.day05;

//看父类，建子类
//父类重点看成员的体现，主要有成员变量、成员函数以及构造函数
public class ExtendsDemo {

	public static void main(String[] args) {
		Dog dog = new Dog();
		dog.setName("旺财");
		dog.setAge(11);
		dog.weight = 11.5f;
		// System.out.println(dog.getName() + "年龄为" + dog.getAge() + "重" +
		// dog.weight + "kg" + dog.num);
		// dog.show();
	}

}
