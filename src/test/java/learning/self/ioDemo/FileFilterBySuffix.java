package learning.self.ioDemo;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by qiong.liu on 2018/5/8.
 */
public class FileFilterBySuffix implements FileFilter {
    private String suffix;
    @Override
    public boolean accept(File pathname) {
        return pathname.getName().endsWith(suffix);
    }

    public FileFilterBySuffix(String suffix) {
        this.suffix = suffix;
    }
}
