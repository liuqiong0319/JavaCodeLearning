package learning.work.first.head.test.page537;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by qiong.liu on 2018/12/5.
 * //文件中歌曲的所有属性都要取出来
 */
public class Jukebox2 {
    ArrayList<Song> songList=new ArrayList<Song>();

    public static void main(String[] args) {
        new Jukebox2().go();
    }

    private void go() {
        getSongs();
        System.out.println(songList);
        Collections.sort(songList);
        System.out.println(songList);
        SingerSort singerSort=new SingerSort();
        Collections.sort(songList,singerSort);
        System.out.println(songList);
        TimeSort timeSort=new TimeSort();
        Collections.sort(songList,timeSort);
        System.out.println(songList);
    }

    private void getSongs() {
        try{
            BufferedReader br=new BufferedReader(new FileReader("/Users/qiong.liu/Documents/writeDemo.txt"));
            String line=null;
            while ((line=br.readLine())!=null){
                String[] temp=line.split("/");
                Song song=new Song(temp[0],temp[1],temp[2],temp[3]);
                songList.add(song);

            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
