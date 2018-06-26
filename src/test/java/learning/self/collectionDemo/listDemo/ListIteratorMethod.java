package learning.self.collectionDemo.listDemo;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List;

/**
 * Created by qiong.liu on 2018/3/24.
 *
 * ListIterator方法
 * 获取集合中的元素。
 *  如果集合中有元素等于 hello2.那么就插入一个新的元素，world。
 */
public class ListIteratorMethod {

    public static List listIteratorDemo(){
        List list=new ArrayList();
        list.add("hello1");
        list.add("hello2");
        list.add("hello3");
        list.add("hello4");

        //此种方法会抛出异常:ConcurrentModificationException:当方法检测到对象的并发修改，但不允许这种修改时，抛出此异常。
//        for(Iterator it = list.iterator(); it.hasNext();) {
        for(ListIterator it = list.listIterator(); it.hasNext();){
            Object o=it.next();
            if("hello2".equals(o)){
//                list.add("world");//使用List的listIterator方法后需要使用ListIterator的add方法进行元素添加,否则同样会报异常
//                it.add("world");//添加
                it.set("world");//替换
            }
        }


//        ListIterator it = list.listIterator();
//
//        while(it.hasNext()){
//
//            Object obj = it.next();
//            if("itcast2".equals(obj)){
////				list.add("java");
////				it.add("java");
//                it.set("java");
//            }
//        }

        return list;
    }

    public static void main(String[] args) {
        List list=listIteratorDemo();
        System.out.println(list);
    }
}
