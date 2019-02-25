package java.learning.self.day09.bankStoreMoney;

/**
 * Created by qiong.liu on 2017/12/23.
 */
public class Consumer implements Runnable {
    private Bank bank;
    private int crash;
    private Object obj=new Object();
    public Consumer(int crash,Bank bank){
        this.crash=crash;
        this.bank=bank;
    }
    public void run() {//加同步锁,保证每个运行中的进程在进行存入工作和输出工作在完成后,再让下个进程进入
        synchronized (obj) {
            for (int i = 1; i < 4; i++) {
                System.out.println(Thread.currentThread().getName() + "本次存入金额" + crash);
                bank.store(crash);
            }
        }
    }
}

