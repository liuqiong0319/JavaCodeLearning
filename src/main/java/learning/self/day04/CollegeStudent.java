package java.learning.self.day04;

public class CollegeStudent extends Student {
	public void grade(){
		System.out.println("���䣺"+age+"��ѧ��������Ӧ�İ༶");
	}
	public CollegeStudent(){
		System.out.println("CollegeStudent��ִ����");
	}
	
	public void show(){
		System.out.println("ʹ�ø��������age:"+super.age);
	}
}
