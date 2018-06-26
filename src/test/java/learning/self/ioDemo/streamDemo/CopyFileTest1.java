package learning.self.ioDemo.streamDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/4/25.
 */
public class CopyFileTest1 {
    //需求:复制一个已存在的文件到另一个文件中
    public static void main(String[] args) throws IOException{

        //定义源文件和目标文件
        File srcFile=new File("/Users/qiong.liu/Documents/460.xlsx");
        File desFile=new File("/Users/qiong.liu/Documents/460_cppy.xlsx");
        //定义输入输出流
        FileInputStream fis=new FileInputStream(srcFile);
        FileOutputStream fos=new FileOutputStream(desFile);
        //读取字节并存入
        int ch=0;
        while((ch=fis.read())!=-1){
            fos.write(ch);
        }
        //关闭输入输出流
        fis.close();
        fos.close();


    }
}
