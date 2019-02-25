package java.learning.self.day08;

/**
 * Created by qiong.liu on 2017/12/19.
 */
public class ArrayOutOfBoundException  extends RuntimeException{
    public ArrayOutOfBoundException() {
    }

    public ArrayOutOfBoundException(String message) {
        super(message);
    }

    public ArrayOutOfBoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
