package java.learning.self.ioDemo.streamDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/4/25.
 */
public class CopyFileTest2 {
    public static void main(String[] args) throws IOException {
        File srcFile=new File("/Users/qiong.liu/Documents/460.xlsx");
        File desFile=new File("/Users/qiong.liu/Documents/460_copy2.xlsx");

        FileInputStream fis=new FileInputStream(srcFile);
        FileOutputStream fos=new FileOutputStream(desFile);

        byte[] buf=new byte[1024];
        int len=0;
        while((len=fis.read())!=-1){
            fos.write(buf,0,len);
        }

        fis.close();
        fos.close();
    }
}
