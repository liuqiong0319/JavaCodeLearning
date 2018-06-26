package learning.self.ioDemo.readerWriterDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by qiong.liu on 2018/5/15.
 */
public class ReadFileFromKeyBoard {

    //从键盘中读入数据

    public static void main(String[] args) throws IOException {
        InputStream in=System.in;

        BufferedReader buf=new BufferedReader(new InputStreamReader(in));

        String line = null;
        while((line=buf.readLine())!=null){

            if("over".equals(line)){
                break;
            }
            System.out.println(line);

        }
    }
}
