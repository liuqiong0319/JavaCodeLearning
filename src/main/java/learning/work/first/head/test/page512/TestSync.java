package learning.work.first.head.test.page512;

/**
 * Created by qiong.liu on 2018/11/26.
 */
public class TestSync implements Runnable {
    private  int balance;
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            increment();
            System.out.println(Thread.currentThread().getName()+" balance is "+balance);
        }
    }

    private synchronized void increment() {
        int i=balance;
        balance=i+1;
    }

    public static void main(String[] args) {
        TestSync job=new TestSync();
        Thread a=new Thread(job);
        Thread b=new Thread(job);
        a.setName("Thread-A");
        b.setName("Thread-B");
        a.start();
        b.start();

    }
}
