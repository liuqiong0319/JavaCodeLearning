package learning.self.ThreadDemo.SellTicketsDemo;

/**
 * 售票系统,4个窗口同时售票,售票张数>0;当出现线程安全问题时,会导致售票张数<=0;解决方法:使用同步代码块即锁synchronized
 * Created by qiong.liu on 2018/3/13.
 */
public class TicketsSellMethod implements Runnable{
    int tickets=100;
    Object obj=new Object();
    public void run() {
        synchronized (obj) {
            while (true) {
                if (tickets > 0) {
                    try {
                        Thread.sleep(1);
                        System.out.println(Thread.currentThread().getName() + "正在售票" + tickets--);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }
}
