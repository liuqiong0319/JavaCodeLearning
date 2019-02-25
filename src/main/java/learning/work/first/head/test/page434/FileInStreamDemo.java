package learning.work.first.head.test.page434;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by qiong.liu on 2018/11/16.
 */
public class FileInStreamDemo {
    public static void main(String[] args) throws Exception {
        FileInputStream fs=new FileInputStream("/Users/qiong.liu/Documents/test.txt");
        ObjectInputStream ois=new ObjectInputStream(fs);
        Object one=ois.readObject();

        Foo myFoo=(Foo)one;
        myFoo.getHeight();
        myFoo.getWidth();
        System.out.println(myFoo.getHeight()+"...."+myFoo.getWidth());

        ois.close();
    }
}
