package learning.self.day04;

import java.util.*;

public class ArrayTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] number = { 12, 53, 68, 27, 53, 8, 12 };
		String str = "23,34,56,78,34,78,22";
		// int result = arrayAdd(number);
		// int max = arrayMax2(number);
		// int min = arrayMin(number);
		// System.out.println(result);
		// System.out.println(max);
		// System.out.println(min);
		arrayRemoveDupli(number);
		arrayRemoveBySet(str);
	}

	public static int arrayAdd(int[] num) {
		int sum = 0;
		for (int i = 0; i < num.length; i++) {
			sum += num[i];
		}
		return sum;
	}

	public static void arrayRemoveDupli(int[] numList) {// 去重
		// int[] numList = { 5, 6, 35, 45, 11, 25, 6, 35 };
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < numList.length; i++) {
			if (!list.contains(numList[i])) {
				list.add(numList[i]);
			}
		}
		System.out.println("list格式输出---" + list.toString());
		Integer[] numlist2 = list.toArray(new Integer[1]);
		for (int i = 0; i < list.size(); i++) {
			numlist2[i] = list.get(i);
		}
		System.out.println("转化成数组输出---" + Arrays.toString(numlist2));
	}

	public static void arrayRemoveBySet(String str) {
		String[] strlist = str.split(",");
		List<String> list = Arrays.asList(strlist);
		Set<String> setlist = new HashSet<String>(list);
		System.out.println(setlist);

	}

	public static int arrayMax(int[] num) {
		int max = num[0];
		for (int i = 1; i < num.length; i++) {
			max = max > num[i] ? max : num[i];
		}
		return max;
	}

	public static int arrayMax2(int[] num) {
		int max = 0;
		for (int i = 0; i < num.length; i++) {
			max = num[max] > num[i] ? max : i;
		}
		return num[max];
	}

	public static int arrayMin(int[] num) {
		int min = num[0];
		for (int i = 0; i < num.length; i++) {
			min = min <= num[i] ? min : num[i];
		}
		return min;
	}

}
