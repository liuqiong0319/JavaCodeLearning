package learning.self.mapDemo;

import learning.self.mapDemo.domain.Student;

import java.util.*;

/**
 * Created by qiong.liu on 2018/4/8.
 */
public class BasicMapDemo {
    /**
     * 需求:显示学生和所在地区的对应关系
     */

    public static void main(String[] args) {
        Map<Student,String> stu=new HashMap<Student, String>();
        stu.put(new Student("vicky",25),"上海");
        stu.put(new Student("alinda",27),"武汉");
        stu.put(new Student("linda",22),"成都");
        stu.put(new Student("vivi",19),"长沙");
        stu.put(new Student("linda",22),"南京");

        /**
         * map的keySet方法返回值类型是Set,迭代取出的是对应的key,再使用get(key)方法获取对应的value值
         *
         */


        System.out.println("------------keySet方法的获取---------------");
        Set<Student> keySet=stu.keySet();
        for(Iterator<Student> it =keySet.iterator();it.hasNext();){
            Student key=it.next();
            String value=stu.get(key);
            System.out.println(key+"……"+value);
        }


        for(Student key:stu.keySet()){
            String value=stu.get(key);
            System.out.println("key:"+key.toString()+"……value:"+value);

        }

        System.out.println("------------entrySet方法的获取---------------");
        /**
         * 使用entrySet方法获取映射关系,调用方法: Set<Map.Entry<K,V>>,映射项（键-值对）。
         * Map.entrySet 方法返回映射的 collection 视图
         */

        Set<Map.Entry<Student,String>> entrySet=stu.entrySet();//for方法
        for (Iterator<Map.Entry<Student,String>> it = entrySet.iterator();it.hasNext();){
            Map.Entry<Student,String> result= it.next();
            Student key=result.getKey();
            String value=result.getValue();
            System.out.println("key:"+key.toString()+"……value:"+value);
        }

        for(Map.Entry<Student,String>  result : stu.entrySet()){//foreach方法
            Student key=result.getKey();
            String value=result.getValue();
            System.out.println("key:"+key.toString()+"……value:"+value);

        }

        System.out.println("------------获取所有value---------------");
        /**
         * 使用values方法可以获取values集合,类型为collection
         */
        Collection<String> values=stu.values();

        for(Iterator it=values.iterator();it.hasNext();){
            System.out.println(it.next());
        }
        for(String value:values){
            System.out.println("value:"+value);
        }

    }


}
