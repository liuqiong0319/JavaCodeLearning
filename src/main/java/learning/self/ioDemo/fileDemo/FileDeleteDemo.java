package java.learning.self.ioDemo.fileDemo;

import java.io.File;

/**
 * Created by qiong.liu on 2018/5/8.
 */
public class FileDeleteDemo {
    //删除带有内容的目录

    public static void main(String[] args) {
        File file=new File("/Users/qiong.liu/Downloads/java学习/源码的副本");
        removeDir(file);
    }
    public static void removeDir(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for(File file:files){
                if(file.isDirectory()){
                    removeDir(file);
                }
                else{
                    System.out.println(file+":"+file.delete());
                }
            }
        }
        System.out.println(dir+":"+dir.delete());
    }
}
