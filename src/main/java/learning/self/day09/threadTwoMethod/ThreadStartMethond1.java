package java.learning.self.day09.threadTwoMethod;

/**
 * Created by qiong.liu on 2017/12/23.
 * thread:
 * 创建新执行线程有两种方法。
 * 一种方法是将类声明为 Thread 的子类。
 * thread子类中的run方法是空方法,调用start方法时会新建一个线程并调用run方法
 * 故该子类应重写 Thread 类的 run 方法,将线程任务代码定义到run方法中,接下来可以分配并启动该子类的实例.
 * Override是重写方法关键字
 * currentThread()获取当前线程名称,.getName()获取线程名称
 * 主线程的名称： main
 *  自定义的线程： Thread-1  线程多个时，数字顺延。Thread-2......
 */
public class ThreadStartMethond1 extends Thread  {
    private String name;

    public ThreadStartMethond1(String name) {
        this.name = name;
    }


    @Override
    public void run(){
        for(int i=1;i<=50;i++)
        System.out.println("当前线程名:"+ThreadStartMethond1.currentThread().getName()+" name:"+name+"……"+i);
    }
}
