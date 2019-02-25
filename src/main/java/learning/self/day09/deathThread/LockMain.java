package java.learning.self.day09.deathThread;

/**
 * Created by qiong.liu on 2017/12/23.
 */
public class LockMain {
    public static void main(String[] args) {
        DeadLock deadLock1=new DeadLock(true);
        DeadLock deadLock2=new DeadLock(false);
        Thread t1=new Thread(deadLock1);
        Thread t2=new Thread(deadLock2);
        t1.start();
        t2.start();

    }
}
