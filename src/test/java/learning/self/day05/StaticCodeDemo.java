package learning.self.day05;

public class StaticCodeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// StaticCode.print();

		String st = "abcddcaa";
		boolean result = testDemo(st);
		System.out.println(result);
	}

	// 比较string是否对称
	public static boolean testDemo(String st) {
		char[] charArray = st.toCharArray();
		int min = 0;
		int max = charArray.length - 1;
		while (max >= min) {
			if (!(charArray[min++] == charArray[max--]))
				return false;
		}
		return true;
	}

}
