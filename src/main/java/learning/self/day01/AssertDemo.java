package java.learning.self.day01;

/**
 * 1.assert expression1;
 * 2. assert expression1: expression2;
 * Created by joan.liu on 2017/8/31.
 */
public class AssertDemo {
    public static void assertTest(int a) {
        try {
            assert a > 0;
        } catch (AssertionError e) {
            System.out.println(e.getMessage());

        }
    }

    public static void main(String[] args) {
        assertTest(-2);
    }
}
