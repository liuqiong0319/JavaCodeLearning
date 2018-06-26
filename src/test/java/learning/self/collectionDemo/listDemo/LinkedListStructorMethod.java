package learning.self.collectionDemo.listDemo;

import java.util.LinkedList;

/**
 * Created by qiong.liu on 2018/3/24.
 *
 */
public class LinkedListStructorMethod {
    //封装一个链表数据结构
    private LinkedList linkedList;
    LinkedListStructorMethod(){
        linkedList= new LinkedList();
    }

    public  void myAdd(Object obj){
        linkedList.addFirst(obj);

    }

    public  Object myGet(){
         return linkedList.removeFirst();//堆栈
//        return linkedList.removeLast();//队列

    }

    public boolean isNull(){
       return linkedList.isEmpty();
    }
}
