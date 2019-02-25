package java.learning.self.ioDemo.cutFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/5/18.
 */
public class MergeFileTest1 {
    /**
     * 合并文件
     * 思路:将每个碎片文件进行合并
     */

    public static void main(String[] args) throws IOException {
        
        FileInputStream fis1=new FileInputStream("/Users/qiong.liu/Downloads/SplitParts/1.part");
        FileInputStream fis2=new FileInputStream("/Users/qiong.liu/Downloads/SplitParts/2.part");
        FileInputStream fis3=new FileInputStream("/Users/qiong.liu/Downloads/SplitParts/3.part");
        FileInputStream fis4=new FileInputStream("/Users/qiong.liu/Downloads/SplitParts/4.part");
        FileInputStream fis5=new FileInputStream("/Users/qiong.liu/Downloads/SplitParts/5.part");
        FileInputStream fis6=new FileInputStream("/Users/qiong.liu/Downloads/SplitParts/6.part");
        FileInputStream fis7=new FileInputStream("/Users/qiong.liu/Downloads/SplitParts/7.part");
        FileInputStream fis8=new FileInputStream("/Users/qiong.liu/Downloads/SplitParts/8.part");
        FileInputStream fis9=new FileInputStream("/Users/qiong.liu/Downloads/SplitParts/9.part");
        FileInputStream fis10=new FileInputStream("/Users/qiong.liu/Downloads/SplitParts/10.part");
        FileInputStream fis11=new FileInputStream("/Users/qiong.liu/Downloads/SplitParts/11.part");
        FileInputStream fis12=new FileInputStream("/Users/qiong.liu/Downloads/SplitParts/12.part");


        FileOutputStream fos=new FileOutputStream("/Users/qiong.liu/Downloads/BaiduNetdisk.dmg");

        byte[] buf=new byte[1024*1024];

        int length1=fis1.read(buf);
        fos.write(buf,0,length1);
        int length2=fis2.read(buf);
        fos.write(buf,0,length2);
        int length3=fis3.read(buf);
        fos.write(buf,0,length3);
        int length4=fis4.read(buf);
        fos.write(buf,0,length4);
        int length5=fis5.read(buf);
        fos.write(buf,0,length5);
        int length6=fis6.read(buf);
        fos.write(buf,0,length6);
        int length7=fis7.read(buf);
        fos.write(buf,0,length7);
        int length8=fis8.read(buf);
        fos.write(buf,0,length8);
        int length9=fis9.read(buf);
        fos.write(buf,0,length9);
        int length10=fis10.read(buf);
        fos.write(buf,0,length10);
        int length11=fis11.read(buf);
        fos.write(buf,0,length11);
        int length12=fis12.read(buf);
        fos.write(buf,0,length12);

        fos.close();
        fis1.close();
        fis2.close();
        fis3.close();
        fis4.close();
        fis5.close();
        fis6.close();
        fis7.close();
        fis8.close();
        fis9.close();
        fis10.close();
        fis11.close();
        fis12.close();

    }
}
