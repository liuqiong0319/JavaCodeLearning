package learning.work.first.head.test.page310;

/**
 * Created by qiong.liu on 2018/10/26.
 *
 * 执行分析
 * 1,静态变量和方法随类加载
 * 2,先执行静态变量:子类和父类依次进栈,执行完父类-子类的静态变量后出栈.此处先执行父类的static代码块,再执行子类的static代码块;
 * 3,再执行静态方法:查看是否有静态方法,无,则从上往下执行main方法
 * 4,输出in main 语句
 * 5,子类创建了一个新的实例,子类和父类的构造方法依次进栈,执行完父类-子类的构造方法后,出栈.此处先执行父类的构造方法,再执行子类的构造方法
 */
public class StaticTests extends StaticSuper{
    static int rand;
    static {
        rand=(int)(Math.random()*6);
        System.out.println("static block "+ rand);
    }
    StaticTests(){
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        System.out.println("in main");
        StaticTests st=new StaticTests();
    }
}
