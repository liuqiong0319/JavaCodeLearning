package java.learning.self.ioDemo.readerWriterDemo;

import java.io.*;

/**
 * Created by qiong.liu on 2018/5/15.
 */
public class KeyboardToFile {

    public static void main(String[] args)  throws IOException{

        BufferedReader bufr=new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bufw=new BufferedWriter(new FileWriter("testa.txt"));

        String line =null;
         while ((line=bufr.readLine())!=null){
             if("over".equals(line)){
                 break;
             }
            bufw.write(line);
             bufw.newLine();
             bufw.flush();
        }

        bufw.close();

    }
}
