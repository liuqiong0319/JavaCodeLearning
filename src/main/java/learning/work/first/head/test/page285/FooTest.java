package learning.work.first.head.test.page285;

/**
 * Created by qiong.liu on 2018/10/25.
 */
public class FooTest {
    public static void main(String[] args) {

        System.out.println(Foo.x);
        Foo foo=new Foo();
        foo.go();

        Foo2 foo2=new Foo2();
        foo2.go();

        System.out.println(Foo4.x);
        Foo4 foo4=new  Foo4();
        foo4.go();

        System.out.println(Foo5.x);
        Foo5 foo5=new Foo5();
        foo5.go(6);

        Foo6.go(9);
        Foo6 foo6=new Foo6();
        System.out.println(foo6.x);
    }
}
