package learning.self.day06.duoTaiDemo1;

public class Cat extends Animal implements CatchMouse {

	public void catchMouse() {
		// TODO Auto-generated method stub
		System.out.println("抓老鼠");
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("吃鱼");
	}

}
