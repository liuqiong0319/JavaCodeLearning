package learning.work.first.head.test.page562;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Created by qiong.liu on 2018/12/28.
 */
public class Jukebox3 {

    ArrayList<Song> songArrayList=new ArrayList<Song>();


    public void songList(){
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader("/Users/qiong.liu/Documents/writeDemo.txt"));
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                String[] temp=line.split("/");
                Song song=new Song(temp[0],temp[1],temp[2],temp[3]);
                songArrayList.add(song);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void go(){
        songList();
        System.out.println(songArrayList);
        Collections.sort(songArrayList);
        System.out.println(songArrayList);
        TreeSet<Song> songHashSet=new TreeSet<Song>();
        songHashSet.addAll(songArrayList);
        System.out.println(songHashSet);

    }

    public static void main(String[] args) {
        new Jukebox3().go();
    }

}
