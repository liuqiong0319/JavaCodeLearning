package java.learning.self.reflect;

import java.lang.reflect.Constructor;

/**
 * Created by qiong.liu on 2018/8/29.
 */
public class ReflectConstructorDemo {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("java.learning.self.reflect.daemon.Student");

        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray=clazz.getConstructors();
        for(Constructor c:conArray){
            System.out.println(c);
        }

        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for(Constructor c:conArray){
            System.out.println(c);
        }

        System.out.println("*****************获取公有的构造方法****************");
        Constructor con=clazz.getConstructor(null);
        System.out.println(con);
        con=clazz.getConstructor(String.class);
        System.out.println(con);
        Object obj=con.newInstance("vicky");//创建实例,并调用有参的构造方法
        System.out.println(obj);



        System.out.println("******************获取私有构造方法，并调用*******************************");
        con=clazz.getDeclaredConstructor(int.class);
        System.out.println(con);

        con.setAccessible(true);
        obj=con.newInstance(23);
//        System.out.println(obj);


    }
}
