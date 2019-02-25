package java.learning.self.day09.singleDemo;

/**
 * Created by qiong.liu on 2017/12/23.
 */
//单例模式懒汉式  并发访问会有安全隐患，所以加入同步机制解决安全问题。
public class Single2 {
    private static Single2 s = null;
    private Single2() {}

    public static Single2 getInstance() {//线程过来每次都要判断锁.减少判断锁的次数可以提高性能
        if (s == null) {//若不添加s==null判断,并没有减少锁的判断次数.
            synchronized (Single2.class) { // 加了判断后,即使有线程在运行过程中被切换出去了,其他线程扔进不来.而且一旦有一个线程执行完,s不为空后,均不需要再判断锁的情况
                if (s == null) {
                    s = new Single2();
                }
            }

        }
        return s;
    }
}
