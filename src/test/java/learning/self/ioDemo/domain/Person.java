package learning.self.ioDemo.domain;

import java.io.Serializable;

/**
 * Created by qiong.liu on 2018/5/22.
 */
public class Person implements Serializable {
    private static String name;//静态数据不能被序列化
    private transient int age;//对于非静态数据也不想被序列化:transient  瞬态

    private static final long serialVersionUID=4L;

    public Person(String name,int age) {
        this.age = age;
        this.name = name;
    }

    public Person() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
