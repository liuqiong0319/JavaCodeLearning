package java.learning.self.day09.threadTwoMethod;

/**
 * Created by qiong.liu on 2017/12/23.
 * thread:
 * 创建线程的第二种方式。实现Runnable接口。
 * 1，定义类实现Runnable接口：避免了继承Thread类的单继承局限性。
 * 2，覆盖接口中的run方法。将线程任务代码定义到run方法中。
 * 3，创建Thread类的对象：只有创建Thread类的对象才可以创建线程。
 * 4，将Runnable接口的子类对象作为参数传递给Thread类的构造函数。
 * 因为线程已被封装到Runnable接口的run方法中，而这个run方法所属于Runnable接口的子类对象，
 * 所以将这个子类对象作为参数传递给Thread的构造函数，这样，线程对象创建时就可以明确要运行的线程的任务。
 * 5，调用Thread类的start方法开启线程。
 */
public class ThreadStartMethond2 implements Runnable  {
    private String name;

    public ThreadStartMethond2(String name) {
        this.name = name;
    }


    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("当前线程名:"+ ThreadStartMethond1.currentThread().getName()+" name:" + name + "……" + i);
        }
    }
}
