package learning.work.first.head.test.page447;

import java.io.FileWriter;

/**
 * Created by qiong.liu on 2018/11/19.
 */
public class WriteFile {
    public static void main(String[] args) throws Exception {
        FileWriter fw=new FileWriter("/Users/qiong.liu/Documents/writeDemo.txt");
        fw.write("hello world!");
        fw.close();
    }
}
