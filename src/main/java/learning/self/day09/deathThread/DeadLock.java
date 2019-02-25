package java.learning.self.day09.deathThread;

/**
 * Created by qiong.liu on 2017/12/23.
 */
public class DeadLock implements Runnable {
    private boolean flag;
    public DeadLock(boolean flag)
    {
        this.flag = flag;
    }
    public void run() {
        if(flag){
            while (true){
                synchronized (MyLock.LOCKA){
                    System.out.println(Thread.currentThread().getName()+"……lockA");
                    synchronized (MyLock.LOCKB){
                        System.out.println(Thread.currentThread().getName()+"……lockB");
                    }
                }
            }
        }
        else{
            while (true){
                synchronized (MyLock.LOCKB){
                    System.out.println(Thread.currentThread().getName()+"……LockB");
                    synchronized (MyLock.LOCKA){
                        System.out.println(Thread.currentThread().getName()+"……LockA");
                    }
                }
            }
        }
    }
}
