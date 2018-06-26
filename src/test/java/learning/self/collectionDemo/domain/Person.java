package learning.self.collectionDemo.domain;

import java.util.Comparator;

/**
 * Created by qiong.liu on 2018/4/2.
 */
public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person p) {
        int temp = this.age - p.age;
        return temp==0? this.name.compareTo(p.name): temp;
    }
}
