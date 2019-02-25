package java.learning.self.day03;

/**
 * @description:Ƕ���ڲ��࣬��������Ϊstatic���ڲ��ࡣ 
 *                                             ����Ϊstatic���ڲ��࣬����Ҫ�ڲ��������ⲿ
 *                                             �����֮�����ϵ��
 *                                             ����˵���ǿ���ֱ������
 *                                             outer.inner����
 *                                             ����Ҫ�����ⲿ�࣬Ҳ����Ҫ�����ڲ��ࡣ
 *                                             Ƕ����
 *                                             ���ͨ���ڲ��໹��һ��������ͨ�ڲ��಻�
 *                                             ���static
 *                                             ���ݺ�static���ԣ�Ҳ���ܰ���Ƕ
 *                                             ���࣬��Ƕ������ԡ�
 *                                             ��Ƕ���಻������Ϊprivate
 *                                             ��һ������Ϊpublic��������á�
 * @author Administrator
 *
 */
public class InnerStaticClassDemo {
	private String name = "joan";
	public static int age = 27;
	public static float score = 85.4f;

	public static class Inner {
		private String name = "joan.liu";
		private static int age = 30;
		String province = "�Ϻ�";

		public void showInner() {
			System.out.println(InnerStaticClassDemo.age);
			// ����ⲿ��ľ�̬��Ա���ڲ���ĳ�Ա������ͬ����ͨ��������.��̬��Ա�������ⲿ��ľ�̬��Ա
			InnerStaticClassDemo innerDemo = new InnerStaticClassDemo();
			System.out.println(innerDemo.name);
			// ��̬�ڲ��಻��ֱ�ӷ����ⲿ��ķǾ�̬��Ա��������ͨ�� new �ⲿ��().��Ա �ķ�ʽ����
			System.out.println(score);
			// ����ⲿ��ľ�̬��Ա���ڲ���ĳ�Ա���Ʋ���ͬ�����ͨ������Ա����ֱ�ӵ����ⲿ��ľ�̬��Ա
			System.out.println(age);
			System.out.println(name);

			System.out.println("this is inner class");
		}
	}

	public void outerShow() {
		System.out.println("this is outer class");
		Inner inner = new Inner();
		inner.showInner();
		System.out.println("�ⲿ���age:" + age);
		System.out.println("�ڲ����age:" + inner.age);
		System.out.println("�ڲ����name" + inner.name);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Inner inner = new Inner();
		// ������̬�ڲ���Ķ���ʱ������Ҫ�ⲿ��Ķ��󣬿���ֱ�Ӵ��� �ڲ��� ������= new �ڲ���();
		inner.showInner();

		System.out.println("---------------------");
		InnerStaticClassDemo outerDemo = new InnerStaticClassDemo();
		outerDemo.outerShow();

	}

}
