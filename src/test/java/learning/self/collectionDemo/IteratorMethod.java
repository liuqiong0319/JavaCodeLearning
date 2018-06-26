package learning.self.collectionDemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by qiong.liu on 2018/3/23.
 * 迭代器
 */
public class IteratorMethod {
    public static void iteratorDemo(Collection col){
        for(Iterator it=col.iterator();it.hasNext();){
            System.out.println(it.next());
        }
    }

    public static void main(String[] args) {
        Collection col=new ArrayList();
        col.add(123123);
        col.add("hello");
        col.add(true);
        col.add(13.56);
        iteratorDemo(col);
    }
}
