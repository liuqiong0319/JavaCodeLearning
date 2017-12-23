package learning.self.day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by joan.liu on 2017/8/1.
 *
 */
public class Demo01 {
	/**
	 * @description:实现输出数组中的最大值、最小值和平均值、排序并输出 Arrays.sort()实现数组升序排列
	 *                                        Arrays.toString()将数组转换成字符串输出
	 */

	public void Test01() {
		int num[] = new int[] { 61, 23, 4, 74, 13, 148, 20 };
		int max = num[0];
		int min = num[0];
		int sum = 0;
		double avg;
		for (int i = 0; i < num.length; i++) {
			max = max >= num[i] ? max : num[i];
			min = min <= num[i] ? min : num[i];
			sum += num[i];
		}
		avg = sum / (num.length);
		System.out.println("max:" + max);
		System.out.println("min:" + min);
		System.out.println("sum:" + sum);
		System.out.println("avg:" + avg);
		Arrays.sort(num);
		System.out.println(Arrays.toString(num));
	}

	// 无参无返回值：二维数组+ArrayList+Array
	public void listScore() {
		int num[][] = new int[2][3];
		ArrayList scorelist = new ArrayList();
		for (int i = 0; i < num.length; i++) {
			System.out.println("###请输入第" + (i + 1) + "个班级同学的成绩###");
			for (int j = 0; j < num[i].length; j++) {
				System.out.println("第" + (j + 1) + "个学生的成绩：");
				Scanner input = new Scanner(System.in);
				int score = input.nextInt();
				num[i][j] = score;
				scorelist.add(num[i][j]);
			}
		}
		for (int i = 0; i < num.length; i++) {
			System.out.println(Arrays.toString(num[i]));
		}

		System.out.println(scorelist);
	}

	// 无参有返回值：获取最大成绩
	public int maxSocre() {
		int socre[] = { 18, 23, 21, 19, 25, 29, 17 };
		int max = socre[0];
		for (int i = 0; i < socre.length; i++) {
			max = max > socre[i] ? max : socre[i];
		}
		return max;
	}

	// 带参无返回值：获取最大成绩
	public void avgSocre(int socre[]) {
		int sum = 0;
		int avg = 0;
		for (int i = 0; i < socre.length; i++) {
			sum += socre[i];
		}
		avg = sum / socre.length;
		System.out.println(avg);
	}

	// 带参带返回值：将考试成绩排序并输出，返回成绩的个数
	public int scoreNum(int[] score) {
		Arrays.sort(score);
		System.out.println(Arrays.toString(score));
		return score.length;
	}

	/**
	 * 创建指定长度的 int 型数组，并生成 100 以内随机数为数组中的每个元素赋值，然后输出数组
	 *
	 * @author joan.liu
	 *
	 */
	public int[] arrayOut(int length) {
		int[] ArrayTest = new int[length];
		for (int i = 0; i < length; i++) {
			ArrayTest[i] = (int) (Math.random() * 100);
		}
		System.out.println(Arrays.toString(ArrayTest));
		return ArrayTest;
	}

	/**
	 * 实现输出考试成绩的前三名
	 * 
	 * @param scoreList
	 */

	public void thirdPrint(int[] scoreList) {
		Arrays.sort(scoreList);
		int num = 0;
		for (int i = scoreList.length - 1; i >= 0; i--) {
			if (scoreList[i] >= 0 && scoreList[i] <= 100) {
				num++;
				if (num > 3) {
					break;
				}
			}
			System.out.println(scoreList[i]);
		}
	}

	public static void main(String[] args) {
		Demo01 maxScore = new Demo01();
		int result = maxScore.maxSocre();
		System.out.println(result);
		int[] score = { 56, 53, 32, -26, 45 };
		maxScore.avgSocre(score);
		int length = maxScore.scoreNum(score);
		System.out.println("共有" + length + "个成绩信息！");
		maxScore.arrayOut(5);
		maxScore.thirdPrint(score);
	}

}
