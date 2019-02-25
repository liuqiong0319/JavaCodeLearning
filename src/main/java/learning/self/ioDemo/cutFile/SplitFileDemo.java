package java.learning.self.ioDemo.cutFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by qiong.liu on 2018/5/17.
 */
public class SplitFileDemo {
    //将文件分隔成n个小文件

    private static final int BUFFER_SIZE = 1048576;//1024*1024
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void main(String[] args) throws IOException {
        File srcFile=new File("/Users/qiong.liu/Downloads/BaiduNetdisk_mac_2.2.0.dmg");
        File desDir=new File("/Users/qiong.liu/Downloads/SplitParts");
        
        splitFile(srcFile,desDir);
    }

    private static void splitFile(File srcFile, File desDir) throws IOException{

        //健壮性判断
        if(!(srcFile.exists()&&srcFile.isFile())){
            throw  new RuntimeException("文件不存在或非文件类型");
        }
        if(!desDir.isDirectory()){
            desDir.mkdirs();
        }

        //切割后会有很多的文件,需要写入写出

        FileInputStream fis=new FileInputStream(srcFile);

        FileOutputStream fos=null;

        byte[] buf=new byte[BUFFER_SIZE];//切割文件大小1M

        int lenth=0;
        int count=1;
        while((lenth=fis.read(buf))!=-1){
            fos=new FileOutputStream(new File(desDir,(count++)+".part"));
            fos.write(buf,0,lenth);
//            fos.close();
        }


        //写入对应的配置信息至另一文件中

        String fileName=srcFile.getName();

        int partCount=count;

        Properties prop=new Properties();//与IO技术结合的集合对象:properties,里面存储的键值均是字符串
        prop.setProperty("filename", srcFile.getName());
        prop.setProperty("partCount",Integer.toString(partCount));

        prop.store(fos,"part file info");

        fos=new FileOutputStream(new File(desDir,count+".properties"));
        fos.write(("fileName:"+fileName+LINE_SEPARATOR).getBytes());
        fos.write(("partCount:"+Integer.toString(partCount)).getBytes());

        fos.close();
        fis.close();




    }
}
