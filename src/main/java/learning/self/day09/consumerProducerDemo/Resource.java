package java.learning.self.day09.consumerProducerDemo;

/**
 * Created by qiong.liu on 2017/12/23.
 */
public class Resource {
    private String name;
    private int number=0;
    private boolean flag=false;//表示没有蛋糕
    public synchronized void pro(String name){
        if(flag)//如果有蛋糕,线程处于等待状态,此时消费者正在消费蛋糕,直到消费完成,再进行生产,同时显示有蛋糕
            try{this.wait();}catch(InterruptedException e){}
        this.name = name + number;
        number++;
        System.out.println(Thread.currentThread().getName() + "......生产者...." + this.name);
        flag=true;
        this.notify();//唤醒消费者
        }

    public synchronized void con(){
        if(!flag)//如果没有蛋糕,线程处于等待状态,此时生产者正在生产蛋糕,直到有蛋糕,再进行消费.消费完成后,同时显示无蛋糕.
            try{this.wait();}catch(InterruptedException e){}
        System.out.println(Thread.currentThread().getName() + "......消费者...." + this.name);
        flag=false;
        this.notify();//唤醒生产者
    }
}
