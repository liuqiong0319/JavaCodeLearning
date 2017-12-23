package learning.self.day09.singleDemo;

/**
 * Created by qiong.liu on 2017/12/23.
 */

//单例模式饿汉式。  多线程并发饿汉式没问题。
public class Single {
    private static final Single s=new Single();
    private Single(){

    }
    public static Single getInstance(){
        return s;
    }
}

