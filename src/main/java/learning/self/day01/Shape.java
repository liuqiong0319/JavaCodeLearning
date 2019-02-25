package java.learning.self.day01;

/**
 * Created by joan.liu on 2017/8/22.
 */
public class Shape {
	private int length;
	private int weigth;

	public int area(int length, int weigth) {
		this.length = length;
		this.weigth = weigth;
		return length * weigth;
	}
}
