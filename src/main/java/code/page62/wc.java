package java.code.page62;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by qiong.liu on 2018/10/30.
 */
public class wc {
    public static void main(String[] args) {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        String line;
        int lineCount=0;
        int charCount=0;
        int wordCount=0;
        try{
            while((line=in.readLine())!=null){

                if (line.equals("OVER")){
                    break;
                }
                lineCount++;
                charCount+=line.length();
                String words[]=line.split("\\W");
                //\\W表示非单词字符,表示标点符号等,此处表示统计单词个数
                wordCount+=words.length;
            }
            System.out.println("wordCount= "+wordCount);
            System.out.println("charCount= "+charCount);
            System.out.println("lineCount= "+lineCount);
        }catch (IOException e){
            System.err.println("Error:"+e.getMessage());
        }
    }
}
