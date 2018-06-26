package learning.self.collectionDemo.domain;

/**
 * Created by qiong.liu on 2018/4/2.
 */
public class Worker extends Person {
    public Worker(String name, int age) {
        super(name, age);
    }

    public Worker() {
    }

    @Override
    public String toString() {
        return "Worker [name =" + getName() + ", age =" + getAge()
                + "]";
    }
}
