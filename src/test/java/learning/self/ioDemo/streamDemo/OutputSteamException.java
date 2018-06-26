package learning.self.ioDemo.streamDemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/4/19.
 */
public class OutputSteamException {
    public static void main(String[] args) {
        File dir=new File("/Users/qiong.liu/Documents/FileDemo");//当文件不存在时,会报FileNotFoundException,此时会引发文件关闭的NullPointerException
        File file=new File(dir,"test.txt");
        FileOutputStream fos=null;
        try {
             fos= new FileOutputStream(file,true);
            fos.write("你好中国".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
