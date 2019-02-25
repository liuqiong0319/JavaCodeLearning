package java.learning.self.day09.ticketSell;

/**
 * Created by qiong.liu on 2017/12/23.
 */
public class TicketSell2 implements Runnable {
    private static int ticketCount=100;
//    private int ticketCount=100;
    private boolean flag=true;
//    private Object obj=new Object();

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void run() {//run方法覆盖的是接口Runnable的方法,接口中为定义该异常,故需要捕获
        if(flag){
                while (true) {
                    synchronized (TicketSell2.class){//如果函数是静态函数,该函数随着类的加载而加载,只有本类对象,不唯一.
                        // static 同步函数，使用的锁不是this，而是字节码文件对象, 类名.class
//                    synchronized (this) {//必须是同一个锁,可以使用this表示当前锁
                        if (ticketCount > 0) {
                            try{Thread.sleep(10);}catch(InterruptedException e){}
                            System.out.println(Thread.currentThread().getName() + "卖票true:" + ticketCount--);
                        }
                    }
                }
        }
        else {
            while (true) {
                sale();
            }
        }
    }
    //同步函数,具有同步功能的函数,用synchronized修饰方法体
        public static synchronized void sale(){
//            public synchronized void sale(){
            if (ticketCount > 0) {
                try{Thread.sleep(10);}catch(InterruptedException e){}
                System.out.println(Thread.currentThread().getName() + "卖票false:" + ticketCount--);
            }
        }


    }
