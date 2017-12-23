package learning.self.day08;

/**
 * Created by qiong.liu on 2017/12/18.
 * @descrition:创建自定义异常,继承父类Exception
 */
public class NullPointExceptionDemo extends RuntimeException{

    public NullPointExceptionDemo() {
    }

    public NullPointExceptionDemo(String message) {
        super(message);
    }

    public NullPointExceptionDemo(Throwable cause) {
        super(cause);
    }
}
