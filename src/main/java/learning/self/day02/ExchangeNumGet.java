package java.learning.self.day02;

public class ExchangeNumGet {

	public static void main(String[] args) {
		int a = 7;
		int b = 3;
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println("a=" + a + " ;b=" + b);
	}
}
