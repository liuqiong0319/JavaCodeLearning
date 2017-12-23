package learning.self.day05;

//描述超人---单例模式 
public class SingleTest {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static SingleTest st = new SingleTest("superman");

	private SingleTest(String name) {
		this.name = name;
	}

	public static SingleTest getInstance() {
		return st;
	}

	public void fly() {
		System.out.println(this.name + " can fly");
	}

	public static void main(String[] args) {
		SingleTest st1 = SingleTest.getInstance();
		SingleTest st2 = SingleTest.getInstance();
		st1.fly();
		st2.setName("hero");
		st1.fly();
		st2.fly();

	}
}
