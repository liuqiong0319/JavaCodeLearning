package learning.work.first.head.test.page537;

import java.util.Comparator;

/**
 * Created by qiong.liu on 2018/12/28.
 */
public class SingerSort implements Comparator<Song> {
    @Override
    public int compare(Song o1, Song o2) {
        return o1.getSinger().compareTo(o2.getSinger());
    }
}
