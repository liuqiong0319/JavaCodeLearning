package java.learning.self.ioDemo;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by qiong.liu on 2018/5/25.
 */
public class RandomAccessFileDemo {

    public static void main(String[] args) throws IOException {

//        readRandomAccessFile();
        writeRandomWriteFile();


    }

    private static void readRandomAccessFile() throws IOException {
        RandomAccessFile raf=new RandomAccessFile("/Users/qiong.liu/Downloads/random.txt","r");
        byte[] buff=new byte[4];
        raf.read(buff);
        String name=new String(buff);
        int age=raf.readInt();
        System.out.println(name+"::::"+age);
        raf.close();

    }

    private static void writeRandomWriteFile() throws IOException {
        //随机读写,数据需要有规律:使用RandomAccessFile时,需要明确要操作的数据位置.getFilePointer
        RandomAccessFile raf=new RandomAccessFile("/Users/qiong.liu/Downloads/random.txt","rw");
//        raf.write("张三".getBytes());
//        raf.writeInt(97);
//        raf.write("李四".getBytes());
//        raf.writeInt(98);

        raf.seek(10*4);
        raf.write("王哥".getBytes());
        raf.writeInt(99);

        System.out.println(raf.getFilePointer());//获取文件指针
        raf.close();
    }
}
