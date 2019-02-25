package java.learning.self.ioDemo.cutFile;

import java.io.*;
import java.util.*;

/**
 * Created by qiong.liu on 2018/5/22.
 */
public class MergeFileTest3 {

    public static void main(String[] args)  throws IOException {
        File partsDir=new File("/Users/qiong.liu/Downloads/SplitParts");

        mergeFile(partsDir);
    }

    private static void mergeFile(File partsDir) throws IOException {
        //使用IO包中的SequenceInputStream对碎片进行合并,将多个读取流合并成一个读取流

        //获取配置文件
        File configFile=getConfigFile(partsDir);
        //获取配置信息属性集
        Properties prop=getProperties(configFile);
        //将属性集对象传递给文件进行合并
        int proCount=Integer.parseInt(prop.getProperty("partCount"));
        File[] files=partsDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".part");
            }
        });
        int fileCount=files.length;
        if ((proCount-1)!=fileCount){
            throw new RuntimeException("文件下载个数与配置文件不对");
        }
         merge(partsDir,prop);
    }

    //根据配置文件对象获取配置信息属性集
    private static Properties getProperties(File configFile) throws IOException {
        //1,判断碎片文件目录中是否存在properties文件

        FileInputStream fis=null;
        Properties prop=new Properties();
        try{
            fis=new FileInputStream(configFile);
            prop.load(fis);
        }finally {
            if (fis!=null){
                try{
                    fis.close();
                }catch(IOException e){
                    //写日志，记录异常信息。便于维护。
                }
            }
        }

        return  prop;
    }

    //根据碎片目录获取配置文件对象
    private static File getConfigFile(File partsDir) {
        if(!(partsDir.exists()&&partsDir.isDirectory())){
            throw new RuntimeException(partsDir.toString()+"不是有效目录");
        }

        File[] files=partsDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".properties");
            }
        });

        if (files.length!=1){
            throw new RuntimeException(partsDir.toString()+"属性文件不存在或不唯一");
        }

        File configFile=files[0];

        return configFile;
    }

    private static void merge(File partsDir,Properties prop) throws IOException {

//        获取属性集中的信息
        String name=prop.getProperty("fileName");
        int count=Integer.parseInt(prop.getProperty("partCount"));

        List<FileInputStream> list=new ArrayList<FileInputStream>();
        for (int i=1;i<count;i++){
            list.add(new FileInputStream(new File(partsDir,i+".part")));

        }

        Enumeration<FileInputStream> en= Collections.enumeration(list);
        SequenceInputStream sis=new SequenceInputStream(en);

        FileOutputStream fos=new FileOutputStream(new File(partsDir,name));

        byte[] buf=new byte[1024*1024];

        int len=0;
        while ((len=sis.read(buf))!=-1){
            fos.write(buf,0,len);
        }

        fos.close();
        sis.close();
    }
}
