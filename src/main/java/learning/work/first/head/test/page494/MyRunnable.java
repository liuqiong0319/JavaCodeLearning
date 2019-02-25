package learning.work.first.head.test.page494;

/**
 * Created by qiong.liu on 2018/11/26.
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        go();
        System.out.println(Thread.currentThread().getName()+" is working");

    }

    public void go(){
        doMore();
    }

    public void doMore() {
        System.out.println("Hello!!!");
    }
}
