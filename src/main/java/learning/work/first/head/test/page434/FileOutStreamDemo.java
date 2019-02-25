package learning.work.first.head.test.page434;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by qiong.liu on 2018/11/16.
 */
public class FileOutStreamDemo {
    public static void main(String[] args) throws Exception{
        FileOutputStream fos=new FileOutputStream("/Users/qiong.liu/Documents/test.txt");
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        Foo myFoo=new Foo();
        myFoo.setHeight(30);
        myFoo.setWidth(19);
        oos.writeObject(myFoo);
        oos.close();
    }
}
