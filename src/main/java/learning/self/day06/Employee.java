package java.learning.self.day06;

/**
 * 需求：公司中程序员有姓名，工号，薪水，工作内容。 项目经理除了有姓名，工号，薪水，还有奖金，工作内容。 对给出需求进行数据建模。
 * 
 * @author Administrator
 *
 */
public abstract class Employee {

	private String name;
	private int num;
	private float salary;

	public Employee(String name, int num, float salary) {
		super();
		this.name = name;
		this.num = num;
		this.salary = salary;
	}

	public abstract void work();

}
