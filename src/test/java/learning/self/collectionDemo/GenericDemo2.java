package learning.self.collectionDemo;

import learning.self.collectionDemo.domain.Person;
import learning.self.collectionDemo.domain.Student;

import java.util.*;

/**
 * Created by qiong.liu on 2018/4/3.
 * 获取集合中元素的最大值。
 */
public class GenericDemo2 {

    public static void main(String[] args) {
        TreeSet<Person> treeSet=new TreeSet<Person>();
        treeSet.add(new Person("xiaoming1", 30));
        treeSet.add(new Person("xiaoming2", 36));
        treeSet.add(new Person("xiaoming3", 22));
//        Student stu=getMax(co1);
        System.out.println(treeSet);
    }
}
