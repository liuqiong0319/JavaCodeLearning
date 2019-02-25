package java.learning.self.day08;

import lombok.Data;

/**
 * Created by qiong.liu on 2017/12/21.
 */
@Data
public class Person {
    private String name;
    private int age;


    public Person(String name, int age) {
        if(age<=0 || age>=200){
            throw new AgeOutOfLimitException(age+"数值异常了!");
        }
        this.name = name;
        this.age = age;
    }
}
