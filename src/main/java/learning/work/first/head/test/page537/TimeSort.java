package learning.work.first.head.test.page537;

import java.util.Comparator;

/**
 * Created by qiong.liu on 2018/12/28.
 */
public class TimeSort implements Comparator<Song> {
    @Override
    public int compare(Song o1, Song o2) {
        int time1=getInt(o1.getTime());
        int time2=getInt(o2.getTime());
        return time1-time2;
    }
    public int getInt(String str){
        String[] temp=str.split("s");
        int time=Integer.parseInt(temp[0]);
        return time;
    }
}
