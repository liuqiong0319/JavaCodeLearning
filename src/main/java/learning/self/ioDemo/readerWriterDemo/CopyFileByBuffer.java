package java.learning.self.ioDemo.readerWriterDemo;

import java.io.*;

/**
 * Created by qiong.liu on 2018/5/10.
 */
public class CopyFileByBuffer {

    public static void main(String[] args) throws IOException {
        BufferedReader  bufr=new BufferedReader(new FileReader("/Users/qiong.liu/Downloads/test.sql"));

        BufferedWriter bufw=new BufferedWriter(new FileWriter("/Users/qiong.liu/Downloads/test_bufCopy.sql"));


        //方法一: 指定大小字符读取
//
//        char[] buf=new char[1024];
//        int length=0;
//        while ((length=bufr.read(buf))!=-1){
//            bufw.write(buf,0,length);
//        }


        //方法二:使用BufferReader特有方法
        String line=null;
        while((line=bufr.readLine())!=null){
            bufw.write(line);
            bufw.newLine();
            bufw.flush();

        }


        bufr.close();
        bufw.close();
//        String line=null;




    }
}
