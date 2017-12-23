package learning.self.day06.object;

public class ObjectDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1 = new Person(11);
		Person p2 = new Person(12);
		Person p3 = new Person(11);
		System.out.println(p1.equals(p3));
		System.out.println(p1.toString());
		// System.out.println(p1.compare(p2));
		// System.out.println(p1.compare(p3));

	}

}
