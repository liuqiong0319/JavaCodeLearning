package java.learning.self.ioDemo.fileDemo;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by qiong.liu on 2018/4/17.
 */
public class FileDirFilterImpl implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory();
    }
}
