package java.learning.self.ioDemo.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by qiong.liu on 2018/5/8.
 */
public class CompareStudentScore {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static void main(String[] args) throws IOException {
        Set<Student> set=new TreeSet<Student>(Collections.reverseOrder());

        set.add(new Student("李四",20,20,20));
        set.add(new Student("旺财",10,10,20));
        set.add(new Student("小明",60,30,70));
        set.add(new Student("小红",80,90,80));
        set.add(new Student("小强",20,70,20));

        File dir=new File("tempFile");
        if(!dir.exists()){
            dir.mkdir();
        }
        File desFile=new File(dir,"temp.txt");
        write2File(set,desFile);


    }

    private static void write2File(Set<Student> set, File desFile) throws IOException {

        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(desFile);
            for(Student stu:set){
                String info = stu.getName()+"\t"+stu.getSum()+LINE_SEPARATOR;
                fos.write(info.getBytes());
            }
        }finally {
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException("系统资源关闭失败");
                }
            }

        }


    }

}
