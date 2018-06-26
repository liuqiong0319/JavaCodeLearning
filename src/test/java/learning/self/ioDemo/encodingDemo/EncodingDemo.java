package learning.self.ioDemo.encodingDemo;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created by qiong.liu on 2018/5/10.
 */
public class EncodingDemo {
    //编码解码问题


    public static void main(String[] args) throws UnsupportedEncodingException {

        //编码:把看得懂的字符串转化为看不懂的字节即string-byte  String.getBytes


        String str="你好";
        byte[] byte1=str.getBytes("utf-8");//默认编码为utf-8:[-28, -67, -96, -27, -91, -67]

        System.out.println(Arrays.toString(byte1));

        //解码:把看不懂的字节转化成看得懂的字符即byte-string  new String(byte)

        String str1=new String(byte1,"GBK");
        System.out.println(str1);

    }


}
