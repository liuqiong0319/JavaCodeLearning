package java.learning.self.ioDemo.streamDemo;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/5/8.
 */
public class ByteStreamDemo {

    public static void main(String[] args) throws IOException {
        /*
		 * 字节输入流。
		 * available();
		 */

        FileInputStream fis=new FileInputStream("/Users/qiong.liu/Documents/FileDemo/test.txt");

        byte[] buf=new byte[fis.available()];//定义了一个刚刚好的数组。 注意：如果文件过大，容易溢出。
        //建议缓冲区的长度最好还是1024的整数倍。

        fis.read(buf);
        System.out.println(new String(buf));
    }

}
