package learning.self.day06.duoTaiDemo1;

public class Dog extends Animal implements LookHome {

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("吃骨头");
	}

	public void lookHome() {
		// TODO Auto-generated method stub
		System.out.println("看家");

	}

}
