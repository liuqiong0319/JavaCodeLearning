package java.learning.self.day02;

public class MaxNumGet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 23;
		int b = 32;
		int c = 12;
		int max;
		max = (a >= b ? a : b);
		max = (max >= c ? max : c);
		System.out.println(max);

	}

}
