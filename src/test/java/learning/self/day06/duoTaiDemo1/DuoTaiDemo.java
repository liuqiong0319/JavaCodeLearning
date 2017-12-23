package learning.self.day06.duoTaiDemo1;

/**
 * 【转型总结】 1，什么时候使用向上转型呢？ 提高程序的扩展性，不关系子类型(子类型被隐藏)。 需要用子类的特有方法吗？不需要，哦了。向上转型。
 * 2，什么时候使用向下转型呢？ 需要使用子类型的特有方法时。 但是一定要使用 instanceof 进行类型的判断。避免发生
 * ClassCastException
 * 
 * @author Administrator
 *
 */
public class DuoTaiDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Dog a = new Dog();
		// method(a);
		// a.lookHome();
		Animal b = new Cat();// 向上类型提升，不能使用子类型所特有的方法。
		method(b);
		Cat c = (Cat) b;// 将b进行向下类型转换，当需要使用子类型的特有内容时，需要使用向下类型转换
		c.catchMouse();
		// 注意：无论向上提升还是向下转型，最终都是子类对象做着类型的变化。

	}

	public static void method(Animal a) {
		a.eat();
		if (a instanceof Cat) {
			// 向下类型装换时需要判断，避免ClassCastException异常的发生
			Cat b = (Cat) a;
			b.catchMouse();
		} else {
			Dog c = (Dog) a;
			c.lookHome();
		}
	}

}
