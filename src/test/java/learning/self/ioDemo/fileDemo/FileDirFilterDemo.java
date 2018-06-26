package learning.self.ioDemo.fileDemo;

import java.io.File;

/**
 * Created by qiong.liu on 2018/4/17.
 */
public class FileDirFilterDemo {
    public static void main(String[] args) {
        File dir=new File("/Users/qiong.liu/Documents/AutoCase/dtqueryTest/dtquery");
        File[] files=dir.listFiles(new FileDirFilterImpl());//文件夹过滤器
        for (File file:files){
            System.out.println(file);
        }
    }
}
