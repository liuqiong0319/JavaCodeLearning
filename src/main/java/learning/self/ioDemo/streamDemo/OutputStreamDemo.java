package java.learning.self.ioDemo.streamDemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/4/19.
 * //输出流
 */
public class OutputStreamDemo {

    private static final String LINE_SEPARATOR =  System.getProperty("line.separator");

    public static void main(String[] args) throws IOException {

        File dir=new File("/Users/qiong.liu/Documents/FileDemo");

        if(!dir.exists()){
            dir.mkdir();
        }
        File file=new File(dir,"test.txt");
//创建一个用于操作文件的字节输出流对象。一创建就必须明确数据存储目的地。
        //输出流目的是文件，会自动创建。如果文件存在，则覆盖。使用true/false来判断写入内容是覆盖还是追加
        FileOutputStream fos=new FileOutputStream(file,true);

            fos.write("adcsaeafg".getBytes());

        String str=LINE_SEPARATOR+"我在测试";
        fos.write(str.getBytes());

                fos.close();
    }
}
