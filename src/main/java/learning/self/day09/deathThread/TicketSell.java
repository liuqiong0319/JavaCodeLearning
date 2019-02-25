package java.learning.self.day09.deathThread;

/**
 * Created by qiong.liu on 2017/12/23.
 * 当锁中嵌套其他锁时,很有可能出现死锁情况
 */
public  class TicketSell implements Runnable {
    private  int ticketCount=100;
    private boolean flag=true;
    private Object obj=new Object();

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void run() {
        if(flag){
            while (true) {
                synchronized (obj){
                    sale();
                }
            }
        }
        else {
            while (true) {
                this.sale();
            }
        }
    }
        public synchronized void sale() {
            synchronized (obj) {
                if (ticketCount > 0) {
                    try {Thread.sleep(10);} catch ( InterruptedException e ) {}
                    System.out.println(Thread.currentThread().getName() + "卖票false:" + ticketCount--);
                }
            }
        }


    }
