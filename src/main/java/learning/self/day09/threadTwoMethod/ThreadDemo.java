package java.learning.self.day09.threadTwoMethod;

/**
 * Created by qiong.liu on 2017/12/23.
 * 调用start方法，开启线程并让线程执行，同时还会告诉jvm去调用run方法
 * 判断进程是否结束判断的标准:是否仍有存活的线程.当结束进程时,所有的线程即自动结束了.
 * 创建thread 只能是thread类或thread子类.
 *
 *
 * 第二种方式实现Runnable接口避免了单继承的局限性，所以较为常用。
 * 实现Runnable接口的方式，更加的符合面向对象，线程分为两部分，一部分线程对象，一部分线程任务。
 * 继承Thread类：线程对象和线程任务耦合在一起。一旦创建Thread类的子类对象，既是线程对象，又有线程任务。
 * 实现runnable接口：将线程任务单独分离出来封装成对象，类型就是Runnable接口类型。
 * Runnable接口对线程对象和线程任务进行解耦。
 *
 *
 */
public class ThreadDemo {

    public static void main(String[] args) {
        //创建线程的第一种方法
//        ThreadStartMethond1 threadDemo1=new ThreadStartMethond1("john");
//        ThreadStartMethond1 threadDemo2=new ThreadStartMethond1("lilin");
//
//        threadDemo2.start();//自定义线程
//        threadDemo1.run();//主线程

       //创建线程的第二种方法
        ThreadStartMethond2 threadStartMethond1=new ThreadStartMethond2("Viki");
        ThreadStartMethond2 threadStartMethond2=new ThreadStartMethond2("Lily");

        Thread thread1=new Thread(threadStartMethond1);
        Thread thread2=new Thread(threadStartMethond2);
        thread1.start();
        thread2.start();
//        new Thread(threadStartMethond1).start();
//        new Thread(threadStartMethond2).start();

    }
}
