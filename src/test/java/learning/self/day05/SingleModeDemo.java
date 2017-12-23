package learning.self.day05;

public class SingleModeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// SingleMode sm1 = SingleMode.sm;
		// SingleMode sm2 = SingleMode.sm;
		SingleMode sm1 = SingleMode.getInstance();
		SingleMode sm2 = SingleMode.getInstance();
		SingleMode1 sm3 = SingleMode1.getInstance();
		SingleMode1 sm4 = SingleMode1.getInstance();
		if (sm1.equals(sm2)) {
			System.out.println("两者指向同一个对象");
		} else
			System.out.println("两者志向不同对象");

		if (sm3.equals(sm4)) {
			System.out.println("两者指向同一个对象");
		} else
			System.out.println("两者志向不同对象");
	}

}
