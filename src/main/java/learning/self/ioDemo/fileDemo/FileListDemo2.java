package java.learning.self.ioDemo.fileDemo;

import java.io.File;

/**
 * Created by qiong.liu on 2018/4/19.
 */
public class FileListDemo2 {
    //不适用递归的方法,获取file的所有目录或文件列表
    //思路:
    // 1.使用容器将取到的文件/文件夹进行存储;
    //2.对取到的文件/文件夹进行判断,若是文件夹,则存储到容器中

    public static void getFile(File dir) {
        QueueMethod<File> queue = new QueueMethod<File>();
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                queue.myAdd(file);
            } else
                System.out.println(file.getName());
        }

        System.out.println("-------------------");
        while (!queue.isNull()) {

            File subDir=queue.myGet();
            System.out.println(subDir.getName());
            File[] subFiles=subDir.listFiles();
            for(File subFile:subFiles ){
                if( subFile.isDirectory()){
                    queue.myAdd(subFile);
                }
                else{
                    System.out.println(subFile.getName());
                }
            }
        }
    }

    public static void main(String[] args) {
        File file=new File("/Users/qiong.liu/Documents/AutoCase/dtqueryTest/");
        getFile(file);
    }
}



