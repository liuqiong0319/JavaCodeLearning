package learning.work.first.head.test.page494;

/**
 * Created by qiong.liu on 2018/11/26.
 */
public class ThreadTester {
    public static void main(String[] args) {
        //方法1:实现runnable接口中的run方法
//        Runnable threadJob=new MyRunnable();
//        Thread myThread=new Thread(threadJob);
        //方法2:继承Thread类并覆盖类中的run方法
        Thread myThread = new MyThread();
        myThread.start();
        System.out.println("World " + Thread.currentThread().getName() + " is working");
    }
}
