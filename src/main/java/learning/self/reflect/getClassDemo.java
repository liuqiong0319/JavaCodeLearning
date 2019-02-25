package java.learning.self.reflect;

import java.learning.self.reflect.daemon.Student;

/**
 * Created by qiong.liu on 2018/8/29.
 * 获取字节码文件方式
 */
public class getClassDemo {
    public static void main(String[] args) throws Exception {

        //方法1,使用object类中的getClass方法,必须先要有指定类
        Student stu1=new Student();
        Class stuClass1 = stu1.getClass();
        System.out.println(stuClass1.getName());//获取类的名称

        //方法2,使用的任意数据类的一个静态成员class,所有的数据类型都具备的一个属性,,必须先要有指定类类
        Class stuClass2=Student.class;
        System.out.println(stuClass2.getName());
        System.out.println(stuClass1 == stuClass2);

        //方法3,使用Class类中的forName方法,通过给定的名称获取对应的字节码文件对象,反射技术中获取类的方式
        Class stuClass3=Class.forName("java.learning.self.reflect.daemon.Student");//必须是绝对路径
        System.out.println(stuClass3.getName());
        System.out.println(stuClass3 == stuClass2);

    }
}
