package learning.work.first.head.test.page88;

/**
 * Created by qiong.liu on 2018/9/27.
 */
public class XCopy {
    public static void main(String[] args) {
        int orig=42;
        XCopy x=new XCopy();
        int y=x.go(orig);
        System.out.println(orig+" "+y);

    }
    int go(int arg){
        arg=arg*2;
        return arg;
    }
}
