package java.learning.self.ioDemo.cutFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by qiong.liu on 2018/5/17.
 */
public class ReaderPartConfigDemo {
    //解析被分割文件的信息


    public static void main(String[] args)  throws IOException  {
        File configFile=new File("/Users/qiong.liu/Downloads/SplitParts/13.properties");
        readPathConfig(configFile);
        
    }

    private static void readPathConfig(File configFile) throws IOException {
        /*
		 * 配置文件规律，只要读取一行文本，按照 : 对文本进行切割即可。
		 */

        BufferedReader bufr=new BufferedReader(new FileReader(configFile));
        String line=null;
        while((line=bufr.readLine())!=null){
            String [] arr=line.split(":");
            System.out.println(Arrays.toString(arr));
        }

        bufr.close();
    }
}
