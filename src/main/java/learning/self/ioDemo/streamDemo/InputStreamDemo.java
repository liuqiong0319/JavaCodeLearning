package java.learning.self.ioDemo.streamDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/4/19.
 */
public class InputStreamDemo {
    public static void main(String[] args) throws IOException {
        File file=new File("/Users/qiong.liu/Documents/FileDemo/test.txt");
        FileInputStream fis=new FileInputStream(file);
        int chs=0;
        while ((chs=fis.read())!=-1){
            System.out.println("ch="+(char)chs);
        }
        fis.close();

    }
}
