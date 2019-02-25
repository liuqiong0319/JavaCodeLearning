package java.learning.self.collectionDemo.listDemo;

/**
 * Created by qiong.liu on 2018/3/24.
 *
 * 依据linkedlist写一个队列/堆栈方法
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedListStructorMethod linkedListStructorMethod=new LinkedListStructorMethod();
        linkedListStructorMethod.myAdd("hello1");
        linkedListStructorMethod.myAdd("hello2");
        linkedListStructorMethod.myAdd("hello3");
        linkedListStructorMethod.myAdd("hello4");

        while(!linkedListStructorMethod.isNull()){
            System.out.println(linkedListStructorMethod.myGet());
        }


    }



}
