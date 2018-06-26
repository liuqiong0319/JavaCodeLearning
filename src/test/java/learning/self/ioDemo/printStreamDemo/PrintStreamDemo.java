package learning.self.ioDemo.printStreamDemo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by qiong.liu on 2018/5/23.
 */
public class PrintStreamDemo {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos=new FileOutputStream("/Users/qiong.liu/Downloads/print.txt");
        PrintStream ps=new PrintStream(fos);

//        ps.print(97);

        ps.print(System.in);
        ps.close();
    }
}
