package learning.self.day09.consumerProducerDemo;

/**
 * Created by qiong.liu on 2017/12/23.
 */
public class Consumer implements Runnable {

    private Resource r;

    public Consumer(Resource r) {
        this.r=r;
    }

    public void run() {
        while(true){
            r.con();
        }

    }
}
