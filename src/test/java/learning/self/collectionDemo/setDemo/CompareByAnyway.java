package learning.self.collectionDemo.setDemo;

import java.util.Comparator;

/**
 * Created by qiong.liu on 2018/3/28.
 */
public class CompareByAnyway implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        int temp=o1.getName().length()-o2.getName().length();
        return  temp==0?o1.getAge()-o2.getAge():temp;
    }
}
