package java.learning.self.ioDemo.fileDemo;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by qiong.liu on 2018/4/17.
 */
public class FileNameFilterImpl implements FilenameFilter {

    private String suffix;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public FileNameFilterImpl(String suffix) {

        this.suffix = suffix;
    }

//    accept
//    boolean accept(File dir,String name)
//    测试指定文件是否应该包含在某一文件列表中。
//    参数：
//    dir- 被找到的文件所在的目录。
//    name - 文件的名称。
//    返回：
//    当且仅当该名称应该包含在文件列表中时返回 true；否则返回 false。
    @Override
    public boolean accept(File dir, String name) {
//        return name.endsWith(suffix);
        return name.contains(suffix);
    }
}
