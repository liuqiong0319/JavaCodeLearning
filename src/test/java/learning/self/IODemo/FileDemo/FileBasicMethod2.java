package learning.self.ioDemo.fileDemo;

import java.io.File;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/4/16.
 */
public class FileBasicMethod2 {

    public static void main(String[] args) throws IOException {
        File file =new File("/Users/qiong.liu/Documents/test.txt");
        boolean b1=file.createNewFile();
        System.out.println("b1="+b1);


        boolean b2=file.delete();//不去回收站,无法删除正在使用的文件
        System.out.println("b2="+b2);

        boolean b3=file.exists();//判断文件是否存在
        System.out.println("b3="+b3);

        //创建文件夹
        File file2=new File("/Users/qiong.liu/Documents/Idea/File");
        boolean b4=file2.mkdirs();
        System.out.println("b4="+b4);


        boolean b5=file2.delete();//删除目录时,若目录中有内容,则无法删除.需清空内容后再进行删除
        System.out.println("b5="+b5);

        //判断文件\目录

        boolean b6=file2.isDirectory();
        boolean b7 =file2.isFile();
        System.out.println("b6="+b6+" b7="+b7);
    }
}
