package learning.work.first.head.test.page503;

/**
 * Created by qiong.liu on 2018/11/26.
 */
public class RunThreads implements Runnable {
    @Override
    public void run() {
        for (int i=0;i<10;i++){
            String threadName=Thread.currentThread().getName();
            System.out.println(threadName+i+" is running");
        }
    }

    public static void main(String[] args) {
        RunThreads runThreads=new RunThreads();
        Thread alpha=new Thread(runThreads);
        Thread beta=new Thread(runThreads);
        alpha.setName("Alpha Thread");
        beta.setName("Beta Thread");
        alpha.start();
        beta.start();

    }
}
