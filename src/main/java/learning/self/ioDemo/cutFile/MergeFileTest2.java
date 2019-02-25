package java.learning.self.ioDemo.cutFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiong.liu on 2018/5/21.
 */
public class MergeFileTest2 {

    public static void main(String[] args) throws IOException {
        List<FileInputStream> list=new ArrayList<FileInputStream>();
        for(int i=1;i<13;i++){
            list.add(new FileInputStream("/Users/qiong.liu/Downloads/SplitParts/"+i+".part"));
        }
        FileOutputStream fos=new FileOutputStream("/Users/qiong.liu/Downloads/BaiduNetdisk.dmg");

        byte[] buff=new byte[1024*1024];

        for (FileInputStream fis:list){
            int len=fis.read(buff);
            fos.write(buff,0,len);
        }
        fos.close();

        for (FileInputStream fis:list){
            fis.close();
        }
    }
}
