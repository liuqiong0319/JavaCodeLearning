package learning.work.first.head.test.page192;

/**
 * Created by qiong.liu on 2018/10/12.
 */
public class Mixed2 {
    public static void main(String[] args) {
        A a=new A();
        A b=new B();
        A c=new C();
        A a2=new C();
        b.m1();
        c.m2();
        a.m3();

        c.m1();
        c.m2();
        c.m3();

        a.m1();
        b.m2();
        c.m3();

        a2.m1();
        a2.m2();
        a2.m3();

    }
}
