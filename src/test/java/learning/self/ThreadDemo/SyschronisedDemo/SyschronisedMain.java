package learning.self.ThreadDemo.SyschronisedDemo;

/**
 * Created by qiong.liu on 2018/3/13.
 */
public class SyschronisedMain {
    public static void main(String[] args) {
        SyschronizedMethod syschronizedMethod=new SyschronizedMethod();
        Thread t1=new Thread(syschronizedMethod);
        Thread t2=new Thread(syschronizedMethod);
        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        syschronizedMethod.flag=false;
        t2.start();
    }
}
