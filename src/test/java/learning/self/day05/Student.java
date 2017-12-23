package learning.self.day05;

public class Student extends Human {
	public void study() {
		System.out.println(super.getName() + "同学" + super.getAge()
				+ "岁且具有学习的能力");
	}

}
