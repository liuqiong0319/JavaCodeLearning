package java.learning.self.ThreadDemo.SingleInstanceDemo;

/**
 * Created by qiong.liu on 2018/3/16.
 *
 *
 *
 * 单例模式（Singleton Pattern）是 Java 中最简单的设计模式之一。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。

 这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象。

 注意：

 1、单例类只能有一个实例。
 2、单例类必须自己创建自己的唯一实例。
 3、单例类必须给所有其他对象提供这一实例。

 单例模式的应用场景:
 文件处理服务:os中是多进程多线程任务,在操作一个文件的时候，就不可避免地出现多个进程或线程同时操作一个文件的现象，所以所有文件的处理必须通过唯一的实例来进行;
 设备管理器.
 1、要求生产唯一序列号。
 2、WEB 中的计数器，不用每次刷新都在数据库里加一次，用单例先缓存起来。
 3、创建的一个对象需要消耗的资源过多，比如 I/O 与数据库的连接等。
 */

//懒汉式,线程不安全
public class SingleInstanceLazyMethod {
    private static  SingleInstanceLazyMethod singleInstanceLazyMethod;

    private SingleInstanceLazyMethod(){};

    public static SingleInstanceLazyMethod getInstance(){
        if (singleInstanceLazyMethod==null)
            singleInstanceLazyMethod=new SingleInstanceLazyMethod();
        return singleInstanceLazyMethod;


    }


}
