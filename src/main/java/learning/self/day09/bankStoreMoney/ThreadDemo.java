package java.learning.self.day09.bankStoreMoney;

/**
 * Created by qiong.liu on 2017/12/23.
 */
public class ThreadDemo {
    public static void main(String[] args) {
        Consumer comsumer = new Consumer(500, new Bank());
        Thread thread1 = new Thread(comsumer);
        Thread thread2 = new Thread(comsumer);
        Thread thread3 = new Thread(comsumer);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}