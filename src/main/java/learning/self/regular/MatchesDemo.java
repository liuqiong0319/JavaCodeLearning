package java.learning.self.regular;

/**
 * Created by qiong.liu on 2018/9/3.
 */
public class MatchesDemo {
    /**
     *     正则表达式--匹配
     *     匹配手机号码
     */

    public static void main(String[] args) {
        matches();
    }

    private static void matches() {
        String number="021123k3432";
        String regex="[0,1]\\d{10}";
        boolean result=number.matches(regex);
        System.out.println(number+":::::::"+result);
    }
}
