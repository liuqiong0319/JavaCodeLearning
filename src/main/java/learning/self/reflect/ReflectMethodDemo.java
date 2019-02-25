package java.learning.self.reflect;

import java.lang.reflect.Method;

/**
 * Created by qiong.liu on 2018/8/29.
 */
public class ReflectMethodDemo {
    public static void main(String[] args) throws Exception{
        Class stuClass=Class.forName("java.learning.self.reflect.daemon.Student");

        System.out.println("***************获取所有的”公有“方法*******************");
        Method[] methodArray = stuClass.getMethods();
        for (Method m:methodArray)
        {
            System.out.println(m);
        }

        System.out.println("***************获取所有的方法，包括私有的*******************");

        methodArray=stuClass.getDeclaredMethods();
        for (Method m:methodArray)
        {
            System.out.println(m);
        }

        System.out.println("***************获取公有的show1()方法*******************");
        Method m=stuClass.getMethod("show1",String.class);
        System.out.println(m);

        Object obj=stuClass.getConstructor().newInstance();
        m.invoke(obj,"芮BB");

        System.out.println("***************获取私有的show4()方法******************");
        m=stuClass.getDeclaredMethod("show4",int.class);
        System.out.println(m);
        m.setAccessible(true);//解除私有限定
        Object result=m.invoke(obj,23);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
        System.out.println(result);

    }
}
