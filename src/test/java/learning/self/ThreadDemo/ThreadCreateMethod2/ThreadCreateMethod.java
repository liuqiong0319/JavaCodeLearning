package learning.self.ThreadDemo.ThreadCreateMethod2;

/**
 * Created by qiong.liu on 2018/3/9.
 */
public class ThreadCreateMethod  implements Runnable {
    String name;

    public ThreadCreateMethod(String name) {
        this.name = name;
    }
    public void run()
    {
        for(int i=1;i<=20;i++)
        {
            System.out.println(name+"........"+Thread.currentThread().getName()+"........"+i);
        }
    }
}
