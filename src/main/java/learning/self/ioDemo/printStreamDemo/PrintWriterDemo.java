package java.learning.self.ioDemo.printStreamDemo;

import java.io.*;

/**
 * Created by qiong.liu on 2018/5/23.
 */
public class PrintWriterDemo {
    //读取键盘录入,将录入的数据保存到文件中

    public static void main(String[] args) throws IOException {
        BufferedReader bufr=new BufferedReader(new InputStreamReader(System.in));

//        PrintWriter pw=new PrintWriter("/Users/qiong.liu/Downloads/print.txt");

        PrintWriter pw=new PrintWriter(System.out,true);

        String line=null;
        while ((line=bufr.readLine())!=null){
            if("over".equals(line))
            {
                break;
            }
            pw.println(line.toUpperCase());
//            pw.flush();
        }


    }
}
