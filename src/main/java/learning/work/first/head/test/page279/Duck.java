package learning.work.first.head.test.page279;

/**
 * Created by qiong.liu on 2018/10/24.
 * 校验静态实例变量和非静态实例变量的区别
 * 静态实例变量:被同类的所有实例所共享的变量,只会在第一次载入的时候进行初始化.
 * 非静态实例变量:在每次创建实例的时候都会被初始化为0
 */
public class Duck {
//    public static int count;//静态实例变量
    public int count;//非静态实例变量
    public Duck(){
        count++;
        System.out.println("count"+count);
    }
}
