package learning.work.first.head.test.page284;

/**
 * Created by qiong.liu on 2018/10/25.
 */
public class StaticDemo {
    //因为静态方法是随着类的加载执行;而非静态方法需要实例创建后才能执行,执行时间在静态方法之后
    //静态方法不能直接调用静态变量和方法,需要对应的实例调用;
    //非静态方法可以直接调用静态变量和方法

    static int num=0;
    public static void main(String[] args) {//静态方法
        staticDemo();//直接调用静态方法
        System.out.println(num);//直接调用静态变量

        StaticDemo staticDemo=new StaticDemo();
        staticDemo.demo();//需要创建实例调用对应的方法;
        System.out.println(staticDemo.num);//直接调用静态变量

    }

    public static void staticDemo(){//静态方法
        System.out.println("this is a static method");
        num++;
    }

    public void demo(){//非静态方法
        System.out.println("this is a instance method");
        staticDemo();//直接调用静态方法
        num++;//直接调用静态参数
    }
}
