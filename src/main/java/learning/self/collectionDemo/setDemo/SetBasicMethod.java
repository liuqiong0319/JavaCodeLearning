package java.learning.self.collectionDemo.setDemo;

import java.util.*;

/**
 * Created by qiong.liu on 2018/3/24.
 *
 * set 无序且不可重复,取出元素的方式只有一种,迭代器
 *
 */
public class SetBasicMethod {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add(11);
        set.add(13);
        set.add(15);
        set.add(17);
        set.add(13);

        for (Iterator it = set.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
    }
}

