package learning.work.first.head.test.page531;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by qiong.liu on 2018/11/28.
 */
public class JukeBox {

    ArrayList<String> songList=new ArrayList<String>();

    //点歌系统,读取文本文件,文件内容已歌名/歌手名格式显示
    public static void main(String[] args) {
        new JukeBox().go();
    }

    private void go() {
        getSongs();
        System.out.println(songList);
    }

    private void getSongs() {
        try {
            BufferedReader br=new BufferedReader(new FileReader(new File("/Users/qiong.liu/Documents/writeDemo.txt")));
            String name=null;
            while ((name=br.readLine())!=null){
                String[] token=name.split("/");
                songList.add(token[0]);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
