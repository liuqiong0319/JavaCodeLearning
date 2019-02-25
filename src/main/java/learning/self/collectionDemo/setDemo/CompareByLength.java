package java.learning.self.collectionDemo.setDemo;

import java.util.Comparator;

/**
 * Created by qiong.liu on 2018/3/28.
 */
public class CompareByLength implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {

        Student str1=(Student)o1;
        Student str2=(Student)o2;
        int temp=str1.getName().length()-str2.getName().length();
        return temp==0?str1.getName().compareTo(str2.getName()):temp;
    }
}
