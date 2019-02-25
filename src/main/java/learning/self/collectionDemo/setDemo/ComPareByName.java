package java.learning.self.collectionDemo.setDemo;

import java.util.Comparator;

/**
 * Created by qiong.liu on 2018/3/26.
 */
public class ComPareByName implements Comparator {

    //为什么Comparator中有两个实现方法,却覆盖其中一个就可以了:
//    int	compare(T o1, T o2) 比较用来排序的两个参数。
//    boolean	equals(Object obj)
    //因为equals是object类的方法,任何类都继承equals,此时已经被覆盖了;除非需要使用多个比较器,来比较两个比较器是否相同.
    @Override
    public int compare(Object o1, Object o2) {

        Student stu1=(Student) o1;
        Student stu2=(Student) o2;
        int temp=stu1.getName().compareTo(stu2.getName());
        return temp==0?stu1.getAge()-stu2.getAge():temp;
    }
}
