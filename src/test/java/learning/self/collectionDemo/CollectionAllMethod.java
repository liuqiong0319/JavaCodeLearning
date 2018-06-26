package learning.self.collectionDemo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by qiong.liu on 2018/3/22.
 */
public class CollectionAllMethod {

    public static void CollectionAll(Collection c1,Collection c2){
        c1.add("hello");
        c1.add("world");

        c2.add("how");
        c2.add("are");
        c2.add("you");
        c2.add("world");

//        c1.addAll(c2);//将c2的元素全部添加至c1中
//        System.out.println(c1);
//        c1.containsAll(c2);//包含
//        System.out.println(c1.containsAll(c2));
//        c1.removeAll(c2);//删除所有和c2相同的元素
//        System.out.println(c1);
        c1.retainAll(c2);//保留和c2中相同的元素
        System.out.println(c1);

    }

    public static void main(String[] args) {
        Collection c1=new ArrayList();
        Collection c2=new ArrayList();
        CollectionAll(c1,c2);
    }
}
