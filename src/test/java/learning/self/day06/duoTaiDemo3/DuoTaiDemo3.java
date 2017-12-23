package learning.self.day06.duoTaiDemo3;

/**
 * 多态中，成员调用的特点。
 * 
 * 1，成员变量。 当子父类中出现同名的成员变量时。 多态调用该变量时： 编译时期：参考的是引用型变量所属的类中是否有被调用的成员变量。没有，编译失败。
 * 运行时期：也是调用引用型变量所属的类中的成员变量。 简单记： 编译运行看左边。
 * 
 * 2，成员函数。 编译，参考左边，如果没有，编译失败。 运行，参考右边的对象所属的类。 编译看左边，运行看右边。 对于成员函数是动态绑定到对象上。(重写)
 * 
 * 3，静态函数。 编译和运行都参考左边。 静态函数是静态的绑定到类上。(静态与对象无关，只跟调用者有关)
 * 
 * @author Administrator
 *
 */
public class DuoTaiDemo3 extends Object {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fu f = new Zi();
		System.out.println(f.num);
		f.show();
		f.method();

	}

}
