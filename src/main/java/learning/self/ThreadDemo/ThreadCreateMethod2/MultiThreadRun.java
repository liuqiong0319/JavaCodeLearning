package java.learning.self.ThreadDemo.ThreadCreateMethod2;

/**
 * Created by qiong.liu on 2018/3/9.
 */
public class MultiThreadRun {
    public static void main(String[] args) {
        ThreadCreateMethod threadCreateMethod1=new ThreadCreateMethod("Joan");
        Thread t1=new Thread(threadCreateMethod1);
        Thread t2=new Thread(threadCreateMethod1);
        t1.start();
        t2.start();
    }
}
