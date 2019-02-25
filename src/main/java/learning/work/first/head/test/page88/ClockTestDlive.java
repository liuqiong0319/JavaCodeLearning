package learning.work.first.head.test.page88;

/**
 * Created by qiong.liu on 2018/9/27.
 */
public class ClockTestDlive {
    public static void main(String[] args) {
        Clock c=new Clock();
        c.setTime("12345");
        String tod=c.getTime();
        System.out.println("time: "+tod);
    }
}
