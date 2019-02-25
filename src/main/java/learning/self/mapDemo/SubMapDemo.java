package java.learning.self.mapDemo;

import java.learning.self.mapDemo.domain.Student;
import java.learning.self.mapDemo.domain.StudentCompableByAge;

import java.util.*;

/**
 * Created by qiong.liu on 2018/4/8.
 */
public class SubMapDemo {
    /**
     * 需求:按照学生姓名自然排序
     */

    public static void main(String[] args) {
//        Map<Student,String> stu=new TreeMap<Student, String>();//需求:按照学生姓名自然排序
        Map<Student,String> stu=new TreeMap<Student, String>(new StudentCompableByAge());//需求:按照学生年龄自然排序
        stu.put(new Student("vicky",25),"上海");
        stu.put(new Student("alinda",19),"武汉");
        stu.put(new Student("linda",22),"成都");
        stu.put(new Student("vivi",19),"长沙");
        stu.put(new Student("linda",22),"南京");

        Set<Student> keySet=stu.keySet();
        for (Iterator<Student> it=keySet.iterator();it.hasNext();)
        {
            Student key=it.next();
            String value=stu.get(key);
            System.out.println(key+"…keySetFor…"+value);
        }

        for (Student key:stu.keySet()){
            String value=stu.get(key);
            System.out.println(key+"…keySetForEach…"+value);
        }



    }
}
