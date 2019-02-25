package java.learning.self.ioDemo.fileDemo;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by qiong.liu on 2018/4/13.
 */
public class FileBasicMethod {

    public static void main(String[] args) {
        File file1=new File("Users/qiong.liu/Documents/zooinspector/target/zooinspector-pkg/bin");
        System.out.println(file1);

        File file2=new File("/Users/qiong.liu/Documents/zooinspector/target","zooinspector-pkg/bin");
        System.out.println(file2);

        File dir=new File("/Users/qiong.liu/Documents/zooinspector/target");
        File file3=new File(dir,"zooinspector-pkg/bin");
        System.out.println(file3);


        File dir2=new File(File.separator+"Users"+File.separator+"qiong.liu"+File.separator+"Documents"+File.separator+"zooinspector"+File.separator+"target");
        File file4=new File(dir2,"zooinspector-pkg"+System.getProperty("file.separator")+"bin");
        System.out.println(file4);


        System.out.println("fileName:"+file4.getName());
        System.out.println("filePath:"+file1.getPath());//取文件路径
        System.out.println(file4.getParent());
        System.out.println(file4.getParentFile());
        System.out.println(file4.getAbsoluteFile());//绝对路径名称,路径信息不全时,路径相对信息取系统的路径信息
        System.out.println(file1.getAbsolutePath());//绝对路径

        long size=file4.length();
        System.out.println("fileSize:"+size);//返回文件大小
        System.out.println("fileFreeSize:"+file4.getFreeSpace());
        Long modifyTime=file4.lastModified();
        Date date=new Date(modifyTime);
        DateFormat dateFormat=DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM, Locale.CHINA);
        String str_date=dateFormat.format(date);
        System.out.println("最后修改时间为"+str_date);

//        String str_date = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG).format(new Date(modifyTime));
//        System.out.println(str_date);
//        System.out.println(file4.getFreeSpace());

    }
}
