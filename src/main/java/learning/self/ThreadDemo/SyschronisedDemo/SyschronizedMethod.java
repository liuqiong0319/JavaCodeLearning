package java.learning.self.ThreadDemo.SyschronisedDemo;

/**
 * 该方法检验同步函数和同步代码块所使用的锁
 * 需求:分别用同步函数和同步代码块进行卖票操作,并人为干预时2个线程分别在对应同步中运行
 * Created by qiong.liu on 2018/3/13.
 */
public class SyschronizedMethod  implements  Runnable{

    private int ticket=100;
    boolean flag=true;
    Object object=new Object();
    public void run() {
        if(flag) {
            while (true) {
//              synchronized (object)//当该锁为object时,会出现线程安全问题,因为此时两个同步非同一个锁,
                synchronized (this)// 将其改成this线程安全问题解决,可以验证同步代码块中使用的锁为this.
                {
                    if (ticket > 0) {
                        try {
                            Thread.sleep(5);
                            System.out.println(Thread.currentThread().getName() + "正在卖票obj" + ticket--);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        else {
            while (true) {
                this.sale();
            }
        }

    }

    //同步代码块
    public synchronized void sale(){
        if(ticket>0){
            try {
                Thread.sleep(5);
                System.out.println(Thread.currentThread().getName()+"正在卖票sale"+ticket--);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
