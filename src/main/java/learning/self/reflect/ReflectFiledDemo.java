package java.learning.self.reflect;

import java.learning.self.reflect.daemon.Student;

import java.lang.reflect.Field;

/**
 * Created by qiong.liu on 2018/8/29.
 */
public class ReflectFiledDemo {
    public static void main(String[] args) throws Exception {
        Class stuClass=Class.forName("java.learning.self.reflect.daemon.Student");

        System.out.println("************获取所有公有的字段********************");
        Field[] fields=stuClass.getFields();
        for(Field f:fields){
            System.out.println(f);
        }

        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        fields=stuClass.getDeclaredFields();
        for (Field f:fields){
            System.out.println(f);
        }
        System.out.println("*************获取公有字段**并调用***********************************");
        Field f=stuClass.getField("name");
        System.out.println(f);

        Object obj=stuClass.getConstructor().newInstance();//产生Student对象--》Student stu = new Student();
        f.set(obj,"慎哥哥");
        Student stu=(Student)obj;
        System.out.println(stu.name);

        System.out.println("**************获取私有字段****并调用********************************");
        f=stuClass.getDeclaredField("phone");
        System.out.println(f);
        f.setAccessible(true);
        f.set(obj,"15612345643");
        System.out.println(stu);

    }
}
