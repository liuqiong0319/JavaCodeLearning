package learning.work.first.head.test.page194;

/**
 * Created by qiong.liu on 2018/10/12.
 */
public class TestBoats {
    public static void main(String[] args) {
        Boat b1=new Boat();
        Sailboat b2=new Sailboat();
        Rowboat b3=new Rowboat();
        b2.setLength(32);
        b1.move();
        b3.move();
        b2.move();
    }
}
