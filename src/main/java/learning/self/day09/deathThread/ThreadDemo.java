package java.learning.self.day09.deathThread;

/**
 * Created by qiong.liu on 2017/12/23.
 * 线程死锁
 */
public class ThreadDemo {
    public static void main(String[] args) {
        TicketSell ticketSell =new TicketSell();
        Thread thread1=new Thread(ticketSell);
        Thread thread2=new Thread(ticketSell);
        thread1.start();
        try{Thread.sleep(10);}catch(InterruptedException e){}
        ticketSell.setFlag(false);
        thread2.start();
    }


}
