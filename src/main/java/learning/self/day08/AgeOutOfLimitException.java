package java.learning.self.day08;

/**
 * Created by qiong.liu on 2017/12/19.
 */
public class AgeOutOfLimitException extends RuntimeException {
    public AgeOutOfLimitException() {
        super();
    }

    public AgeOutOfLimitException(String message) {
        super(message);
    }

    public AgeOutOfLimitException(Throwable cause) {
        super(cause);
    }
}
