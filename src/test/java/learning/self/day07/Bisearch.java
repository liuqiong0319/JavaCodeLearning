package learning.self.day07;

import java.util.Arrays;

/**
 * ???????,??????????? ??????????????????
 * 
 * @author Administrator
 *
 */
public class Bisearch {
	public static void main(String[] args) {
		int[] num = { 12, 34, 45, 67, 78 };
		int result = bisTest2(num, 67);
		int result2 = sort(num, 67);
		int result3 = inserIndex(num, 11);
		upDown(num);
		// System.out.println(result3);

	}

	public static int bisTest(int[] num, int number) {
		int min = 0;
		int max = num.length - 1;
		int mid = (min + max) / 2;
		while (num[mid] != number) {
			if (number > num[mid])
				min = mid + 1;
			else if (number < num[mid])
				max = mid - 1;
			if (max < min)
				return -1;
			mid = (min + max) / 2;
		}
		return mid;
	}

	public static int bisTest2(int[] num, int number) {
		int min = 0;
		int max = num.length - 1;
		while (min <= max) {
			int mid = (min + max) / 2;
			if (number > num[mid])
				min = mid + 1;
			else if (number < num[mid])
				max = mid - 1;
			else
				return mid;

		}
		return -1;
	}

	/*
	 * ???????????в?????????????????????λ?á?
	 */
	public static int inserIndex(int[] num, int number) {
		int max = num.length - 1;
		int min = 0;
		while (max <= min) {
			int mid = (max + min) / 2;
			if (num[mid] > number)
				max = mid - 1;
			else if (num[mid] < number)
				min = mid + 1;
			else
				return mid;
		}
		return min;
	}

	/*
	 * ???????е?????е??
	 */

	public static void upDown(int[] num) {
		for (int start = 0, end = num.length - 1; start < end; start++, end--) {
			int temp = num[start];
			num[start] = num[end];
			num[end] = temp;
		}
		System.out.println(Arrays.toString(num));

	}

	public static int sort(int[] num, int number) {
		for (int i = 0; i < num.length; i++) {
			if (number == num[i])
				return i;
		}
		return -1;
	}
}
