package java.learning.self.ioDemo.streamDemo;

import java.io.*;

/**
 * Created by qiong.liu on 2018/5/8.
 */
public class ByteStreamBufferCopyFileTest {
    //复制文件，通过字节流已有的缓冲区对象。

    public static void main(String[] args) throws IOException {
        File srcFile=new File("/Users/qiong.liu/Documents/460.xlsx");
        File desFile=new File("/Users/qiong.liu/Documents/460_buffer.xlsx");
        FileInputStream fis=new FileInputStream(srcFile);
        FileOutputStream fos=new FileOutputStream(desFile);

        BufferedInputStream bis=new BufferedInputStream(fis);
        BufferedOutputStream bos=new BufferedOutputStream(fos);

        byte[] buff=new byte[1024];
        int len=0;
        while ((len=bis.read(buff))!=-1){
            bos.write(buff,0,len);
            bos.flush();
        }
        bis.close();
        bos.close();
    }
}
