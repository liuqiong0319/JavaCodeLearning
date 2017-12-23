package learning.self.day03;

public class StudentInfo {
	public static void main(String[] args) {
		StrucDemo stu = new StrucDemo();
		stu.setName("joan");
		stu.setNum(2345);
		stu.setScore(66.5f);
		System.out.println(stu.getName() + stu.getNum() + stu.getScore());
	}

}
