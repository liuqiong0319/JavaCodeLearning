package java.learning.self.ioDemo.fileDemo;

import java.io.File;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/4/17.
 */
public class FileNameFilterDemo {

    public static void main(String[] args) throws IOException {
        File dir=new File("/Users/qiong.liu/Documents/AutoCase/dtqueryTest/dtquery/target/classes");
        File[] files=dir.listFiles(new FileNameFilterImpl(".class"));
        for(File file:files) {
            System.out.println(file);
        }
    }
}
