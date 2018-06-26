package learning.self.ioDemo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiong.liu on 2018/5/8.
 */
public class FileFilterDemo {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void main(String[] args) throws  IOException{
//        5，获取指定目录下所有的.java文件(包含子目录中的)，
//		并将这些java文件的绝路路径写入到一个文件中。
//		建立一个java文件清单列表。

        File dir=new File("/Users/qiong.liu/Downloads/java学习/源码");
        FileFilter filter=new FileFilterBySuffix(".java");
        List<File> list=new ArrayList<File>();
        
        getFileList(dir,filter,list);

        File desFile=new File(dir,"javaList.txt");
        write2File(list,desFile);


    }

    private static void write2File(List<File> list, File desFile) throws IOException {
        FileOutputStream fos=null;
        BufferedOutputStream bios=null;
        try{
            fos=new FileOutputStream(desFile);
            bios=new BufferedOutputStream(fos);

            for(File file:list){
                String info=file.getAbsolutePath()+LINE_SEPARATOR;
                bios.write(info.getBytes());
                bios.flush();
            }
        }finally {
            if (bios!=null){
                try{
                    fos.close();
                }
                catch (IOException e){
                    throw new  RuntimeException("关闭失败");
                }
            }
        }
    }

    private static void getFileList(File dir, FileFilter filter,List<File> list) {
        File[] files=dir.listFiles();

        for(File file:files){
            if (file.isDirectory()){
                getFileList(file,filter,list);
            }
            else
            {
                if(filter.accept(file)){
                    list.add(file);
                }
            }
        }
    }
}
