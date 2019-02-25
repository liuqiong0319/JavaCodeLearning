package learning.work.first.head.test.page535;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by qiong.liu on 2018/12/5.
 */
public class JukeBox1 {

    ArrayList<String> songList=new ArrayList<String>();


    public static void main(String[] args) {
        new JukeBox1().go();
    }

    private void go() {
        getSongs();
        System.out.println(songList);
        Collections.sort(songList);
        System.out.println(songList);
    }

    private void getSongs() {
        try {
            File file=new File("/Users/qiong.liu/Documents/writeDemo.txt");
            FileReader reader=new FileReader(file);
            BufferedReader br=new BufferedReader(reader);
            String line=null;
            while ((line=br.readLine())!=null){
                String[] songNames=line.split("/");
                String songName=songNames[0];
                songList.add(songName);
            }
             br.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
