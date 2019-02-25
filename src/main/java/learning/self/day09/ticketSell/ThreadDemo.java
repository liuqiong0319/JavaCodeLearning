package java.learning.self.day09.ticketSell;

/**
 * Created by qiong.liu on 2017/12/23.
 * 多次启动一个线程是非法的。特别是当线程已经结束执行后，不能再重新启动。
 */
public class ThreadDemo {
    public static void main(String[] args) {
//        long startTime=System.currentTimeMillis();
//        TicketSell ticketSell=new TicketSell();
//        Thread thread1=new Thread(ticketSell);
//        thread1.start();
//        Thread thread2=new Thread(ticketSell);
//        thread2.start();
//        Thread thread3=new Thread(ticketSell);
//        thread3.start();
//        Thread thread4=new Thread(ticketSell);
//        thread4.start();
//        long endTime=System.currentTimeMillis();
//        long takeTime=endTime-startTime;
//        System.out.println("程序花费时间:"+takeTime+"ms");
        TicketSell2 ticketSell2=new TicketSell2();
        Thread thread1=new Thread(ticketSell2);
        Thread thread2=new Thread(ticketSell2);
//        Thread thread3=new Thread(ticketSell2);
//        Thread thread4=new Thread(ticketSell2);
        thread1.start();
        try{Thread.sleep(10);}catch(InterruptedException e){}
        ticketSell2.setFlag(false);
        thread2.start();
//        thread3.start();
//        thread4.start();
    }


}
