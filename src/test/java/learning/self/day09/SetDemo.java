package learning.self.day09;

import org.junit.Test;

import java.util.*;

/**
 * Created by qiong.liu on 2017/12/26.
 */
public class SetDemo {

    @Test
    public void listDemo() {
        String[] arrs = {"121","234" ,"2", "31","234","5"};
        int[] arrs2={23,45,67,32,66,45} ;
        List<String> listA = Arrays.asList(arrs);
//        for (int i = 0; i < arrs.length;i++)
//        {
//            if(!listA.contains(arrs[i])){
//                listA.add(arrs[i]);
//            }
//        }
//        System.out.println(listA.toString());
        Set<String> intSet = new HashSet<String>(listA);
        System.out.println(intSet);
    }

}
