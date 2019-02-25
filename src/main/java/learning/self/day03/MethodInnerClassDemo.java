package java.learning.self.day03;

/**
 * @description:�ֲ��ڲ��࣬��ָ�ڲ��ඨ���ڷ�������������
 * @author Administrator
 *
 */
public class MethodInnerClassDemo {

	public void show() {
		class InnerClass {
			int score = 86;

			public int innerShow() {
				System.out.println("this is inner  class method");
				return score + 10;
			}
		}
		InnerClass innerclass = new InnerClass();
		int newscore = innerclass.innerShow();
		System.out.println(newscore);
		System.out.println("this is outer method");
	}

	public static void main(String[] args) {
		MethodInnerClassDemo methodInner = new MethodInnerClassDemo();
		methodInner.show();
	}

}
