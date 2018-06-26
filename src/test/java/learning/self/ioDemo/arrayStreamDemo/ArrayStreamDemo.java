package learning.self.ioDemo.arrayStreamDemo;

import java.io.*;

/**
 * Created by qiong.liu on 2018/5/25.
 */
public class ArrayStreamDemo {
    //字节流
    public static void main(String[] args) throws IOException {
//        arrayByteMethod();
        arrayCharMethod();
        
    }

    private static void arrayCharMethod() throws IOException {

        CharArrayReader car=new CharArrayReader("abcdef".toCharArray());
        CharArrayWriter caw=new CharArrayWriter();
        int ch=0;
        while ((ch=car.read())!=-1){
            caw.write(ch);
        }
        System.out.println(caw.toString());

    }


    private static void arrayByteMethod()  {


        ByteArrayInputStream bis=new ByteArrayInputStream("abcde".getBytes());
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        int ch=0;
        while ((ch=bis.read())!=-1){
            bos.write(ch);
        }
//      public String toString(),使用平台默认的字符集，通过解码字节将缓冲区内容转换为字符串。
        System.out.println(bos.toString());

    }
}
