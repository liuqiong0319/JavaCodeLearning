package java.learning.self.ioDemo.dataStreamDemo;

import java.io.*;

/**
 * Created by qiong.liu on 2018/5/25.
 */
public class DataStreamDemo {

    public static void main(String[] args)  throws IOException {
        writeData();
        readData();

    }

    private static void readData() throws IOException {

        FileInputStream fos=new FileInputStream("/Users/qiong.liu/Downloads/data.txt");
        DataInputStream dis=new DataInputStream(fos);
        int result=dis.readInt();
        System.out.println(result);
        dis.close();
    }

    private static void writeData() throws IOException {
        FileOutputStream fos=new FileOutputStream("/Users/qiong.liu/Downloads/data.txt");
        DataOutputStream dos=new DataOutputStream(fos);
        dos.writeInt(97);
        dos.close();
    }
}
