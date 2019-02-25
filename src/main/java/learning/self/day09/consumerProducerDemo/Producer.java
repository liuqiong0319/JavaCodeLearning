package java.learning.self.day09.consumerProducerDemo;

/**
 * Created by qiong.liu on 2017/12/23.
 */
public class Producer implements Runnable {
    private Resource r;

    public Producer(Resource r) {
        this.r=r;
    }

    public void run() {
        while(true){
            r.pro("面包");
        }

    }
}
