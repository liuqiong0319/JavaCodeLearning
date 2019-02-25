package java.learning.self.ioDemo.readerWriterDemo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/5/10.
 */
public class CopyFile {

    public static void main(String[] args) throws IOException {
        FileReader fr=new FileReader("/Users/qiong.liu/Downloads/test.sql");

        FileWriter fw=new FileWriter("/Users/qiong.liu/Downloads/test_copy.sql");

        char[] buf=new char[1024];
        int len=0;
        while((len=fr.read(buf))!=-1){
            fw.write(buf,0,len);
        }

        fr.close();
        fw.close();


    }
}
