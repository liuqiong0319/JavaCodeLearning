package java.learning.self.ioDemo.fileDemo;

import java.io.File;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/4/17.
 */
public class FileListDemo {

    public static void main(String[] args) throws IOException {
        File dir=new File("/Users/qiong.liu/Documents/AutoCase/dtqueryTest/");
        getFileList(dir);
    }

    public  static void getFileList(File dir) throws IOException{
        System.out.println("dir+++++++:"+dir.getName());
        File[] files=dir.listFiles();
            for(File file:files){
                if(file.isDirectory()){
                    getFileList(file);//递归,当不定义条件时,容易引起栈溢出异常:StackOverflowError.
                }
                else {
                    System.out.println("file:------"+file);
                }
        }
    }
}
