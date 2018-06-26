package learning.self.collectionDemo.setDemo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by qiong.liu on 2018/3/26.
 */
public class HashSetMethod {
    public static void main(String[] args) {
        Set studentSet=new HashSet();
        studentSet.add(new Student("John",23));
        studentSet.add(new Student("jack",25));
        studentSet.add(new Student("vicky",15));
        studentSet.add(new Student("jack",25));
        studentSet.add(new Student("linda",31));
        studentSet.add(new Student("linda",25));
        for (Iterator it = studentSet.iterator(); it.hasNext();)
        {
            System.out.println(it.next());//在未重写student的hashCode和equals方法时,发现并未去重.
        }
    }
}
