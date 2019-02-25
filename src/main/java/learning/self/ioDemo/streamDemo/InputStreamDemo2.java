package java.learning.self.ioDemo.streamDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/4/23.
 */
public class InputStreamDemo2 {
    public static void main(String[] args) throws IOException {
        File file=new File("/Users/qiong.liu/Documents/FileDemo/test.txt");
        FileInputStream fis=new FileInputStream(file);
        byte[] buff=new byte[1024];
        int len=0;
        while ((len=fis.read(buff))!=-1)
            System.out.println(new String(buff,0,len));
    }
}
