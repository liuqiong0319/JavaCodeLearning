package java.learning.self.collectionDemo.listDemo;

import java.util.LinkedList;

/**
 * Created by qiong.liu on 2018/3/24.
 *
 * LinkListed 可用于队列和堆栈  list 有序且可以重复
 * 队列:先进先出
 * 堆栈:先进后出
 *
 */
public class LinkedListBasicMethod {
    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList();
        linkedList.addFirst("ABC1");
        linkedList.addFirst("ABC2");
        linkedList.addFirst("ABC3");
//        System.out.println(linkedList);
//        System.out.println(linkedList.getFirst());
//        System.out.println(linkedList.getLast());

//        System.out.println(linkedList.remove());//移除第一个
//        System.out.println(linkedList.remove());
//        System.out.println(linkedList.remove());
//        System.out.println(linkedList);
        System.out.println(linkedList.size());

        while(!linkedList.isEmpty())
            System.out.println(linkedList.remove());
    }
}
