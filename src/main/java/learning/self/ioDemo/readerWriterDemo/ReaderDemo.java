package java.learning.self.ioDemo.readerWriterDemo;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/5/10.
 */
public class ReaderDemo {
    //FileReader读取字符文件的便捷类

    public static void main(String[] args) throws IOException {


        FileReader fr=new FileReader("/Users/qiong.liu/Downloads/test.sql");

        int ch=0;
        while ((ch=fr.read())!=-1){
            System.out.print((char)ch);
        }

        fr.close();



    }


}
