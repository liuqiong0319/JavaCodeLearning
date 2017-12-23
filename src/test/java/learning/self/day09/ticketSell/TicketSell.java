package learning.self.day09.ticketSell;

import learning.self.day06.object.ObjectDemo;

/**
 * Created by qiong.liu on 2017/12/23.
 */
public class TicketSell implements Runnable {
    private int ticketCount=100;
    private Object obj=new Object();
    public void run() {//run方法覆盖的是接口Runnable的方法,接口中为定义该异常,故需要捕获
        while (true) {
            synchronized (obj) {//必须是同一个锁
                if (ticketCount > 0) {
                    try {
                        Thread.sleep(10);//添加sleep以后可能出现线程安全问题,此时可以使用同步代码块来解决线程安全问题
                    } catch ( InterruptedException e ) {
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票:" + ticketCount--);
                }
            }
        }
    }
    //同步函数,具有同步功能的函数,用synchronized修饰方法体
        public synchronized void sale(){
            if (ticketCount > 0) {
                System.out.println(Thread.currentThread().getName() + "卖票:" + ticketCount--);
            }
        }


    }
