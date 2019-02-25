package java.learning.self.reflect.daemon;

/**
 * Created by qiong.liu on 2018/8/30.
 */
public class Mouse implements USB {
    @Override
    public void open() {
        System.out.println("Mouse run...");

    }

    @Override
    public void close() {
        System.out.println("Mouse close...");


    }
}
