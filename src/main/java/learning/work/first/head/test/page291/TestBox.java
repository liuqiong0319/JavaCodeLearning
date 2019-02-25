package learning.work.first.head.test.page291;

/**
 * Created by qiong.liu on 2018/10/25.
 */
public class TestBox {
    Integer i;//默认值是null
    int j;//默认值是0

    public static void main(String[] args) {
        TestBox testBox=new TestBox();
        testBox.go();
    }
    public void go(){
//        j=i;
        System.out.println(j);
        System.out.println(i);
    }
}
