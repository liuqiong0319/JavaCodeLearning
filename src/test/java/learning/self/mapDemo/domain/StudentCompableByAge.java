package learning.self.mapDemo.domain;

import java.util.Comparator;

/**
 * Created by qiong.liu on 2018/4/8.
 */
public class StudentCompableByAge implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        int temp =o1.getAge()-o2.getAge();
        return temp==0?o1.getName().compareTo(o2.getName()):temp;
    }
}
