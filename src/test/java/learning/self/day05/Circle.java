package learning.self.day05;

public class Circle {
	private double radius;
	private String clour;
	private static double pi = 3.14;

	public Circle(double radius) {
		// TODO Auto-generated constructor stub
		this.radius = radius;
	}

	public Circle(double radius, String clour) {
		// TODO Auto-generated constructor stub
		this(radius); // ����һ���ַ��������Ĺ��캯����ע�⣺�����������캯������䣬
						// ���붨���ڹ��캯���ĵ�һ�С�ԭ�򣺳�ʼ������Ҫ��ִ�С�
		this.clour = clour;
	}

	public double area() {
		return radius * radius * pi;
	}

}
