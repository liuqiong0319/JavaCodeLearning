package learning.self.ThreadDemo.ThreadCreateMethod1;

/**
 * Created by qiong.liu on 2018/3/9.
 */
public class MultiThreadRun {
    public static void main(String[] args) {
        ThreadCreateMethod threadCreateMethod1=new ThreadCreateMethod("Joan");
        ThreadCreateMethod threadCreateMethod2=new ThreadCreateMethod("Lucy");
        threadCreateMethod1.start();
        threadCreateMethod2.start();
    }
}
