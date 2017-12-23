package learning.self.day09.consumerProducerDemo;

/**
 * Created by qiong.liu on 2017/12/23.
 */
public class MainDemo {
    public static void main(String[] args) {
        Resource r=new Resource();
        Producer producer=new Producer(r);
        Consumer consumer=new Consumer(r);
        Thread t1=new Thread(producer);
        Thread t2=new Thread(consumer);
        t1.start();
        t2.start();


    }
}
