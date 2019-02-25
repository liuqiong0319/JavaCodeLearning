package java.learning.self.collectionDemo.domain;

/**
 * Created by qiong.liu on 2018/4/2.
 */
public class Student extends Person {
    public Student(String name, int age) {
        super(name, age);
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student [name =" + getName() + ", age =" + getAge()
                + "]";
    }
}
